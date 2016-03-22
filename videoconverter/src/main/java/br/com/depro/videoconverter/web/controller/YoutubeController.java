package br.com.depro.videoconverter.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.axet.vget.VGet;
import com.github.axet.vget.info.VGetParser;
import com.github.axet.vget.info.VideoInfo;
import com.github.axet.vget.vhs.VimeoInfo;
import com.github.axet.vget.vhs.YoutubeInfo;
import com.github.axet.wget.info.DownloadInfo;

import br.com.depro.videoconverter.model.Video;
import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;

/**
 * @author rsouza
 * @version 1.0 - versao inicial - 05.11.2015
 */
@Controller
@RequestMapping("/youtube")
public class YoutubeController extends ControllerBase {

	private final List<Video> videos = new ArrayList<Video>();
	private String dirVideoOutput;
	private String dirMp3Output;
	
	private VideoInfo info;
	private long last;

	@RequestMapping(value = "/playlisttomp3", method = RequestMethod.GET)
	public String playlisttomp3() {
		return "converter/playlisttomp3";
	}

	@ResponseBody
	@RequestMapping(value = "/playlisttomp3/retrieveurls", method = RequestMethod.POST)
	public ResponseEntity<List<Video>> playlisttomp3Convert(@RequestBody Map<String, String> request) throws Exception {
		this.videos.clear();
		List<Video> infos = new ArrayList<Video>();
		List<String> linhas = new ArrayList<String>();

		this.dirVideoOutput = request.get("videoOutput");
		this.dirMp3Output = request.get("mp3Output");
		
		URL url = new URL(request.get("urlPlaylist"));
		URLConnection conn = url.openConnection();
		conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String l = "";
		while ((l = bufferedReader.readLine()) != null) {
			linhas.add(l);
		}

		List<String> videosNames = null;
		File pathVideo = new File(dirVideoOutput);
		if (!pathVideo.exists()) {
			pathVideo.mkdirs();
		} else {
			videosNames = new ArrayList<String>(Arrays.asList(pathVideo.list()));
		}
		
		List<String> mp3sNames = null;
		File pathMp3 = new File(dirMp3Output);
		if (!pathMp3.exists()) {
			pathMp3.mkdirs();
		} else {
			mp3sNames = new ArrayList<String>(Arrays.asList(pathMp3.list()));
		}
		
		int count = 0;
		Video info = null;
		boolean isCapturaNomeVideo = false;
		for (String linha : linhas) {
			if (linha.contains("watch-appbar-playlist") || count > 0) {
				if (count == 0) {
					count++;
					continue;
				} else if (linha.contains("<div")) {
					count++;
					continue;
				} else if (linha.contains("</div>")) {
					count--;
					continue;
				}

				if (linha.contains("/watch?")) {
					Matcher matcher = Pattern.compile("<a(.*.)href=\"(.*.)\" class").matcher(linha);
					if (matcher.find()) {
						info = new Video();
						info.setUrl("https://www.youtube.com" + matcher.group(2));
					}
				} else if (linha.contains("yt-ui-ellipsis yt-ui-ellipsis-2")) {
					isCapturaNomeVideo = true;
				} else if (isCapturaNomeVideo) {
					String nome = linha.trim().replaceAll("\\|", "").replaceAll("  ", " ");
					info.setNome(StringEscapeUtils.unescapeHtml4(nome));
					//info.setNome(URLDecoder.decode(nome, "UTF-8"));
					//info.setNome(linha.trim());
					infos.add(info);
					
					for (String name : videosNames) {
						if (name.contains(info.getNome())) {
							info.setPorcentagem(new BigDecimal(100));
						}
					}
					
					for (String name : mp3sNames) {
						if (name.contains(info.getNome())) {
							info.setHasMp3(true);
						}
					}
					
					isCapturaNomeVideo = false;
				}
			}
		}
		return new ResponseEntity<List<Video>>(infos, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/playlisttomp3/downloadandconvert", method = RequestMethod.POST)
	public void downloadAndConvert(@RequestBody List<Video> request) throws Exception {
		this.videos.addAll(request);

		File path = new File(dirVideoOutput);
	
		for (Video video : videos) {
			if (video.getDownload() && !video.getHasDownload()) {
				AtomicBoolean stop = new AtomicBoolean(false);
				Runnable notify = createRunnable(video);
				URL web = new URL(video.getUrl());
				VGetParser user =  VGet.parser(web);;
				info = user.info(web);
				VGet v = new VGet(info, path);
				v.extract(user, stop, notify);
				v.download(user, stop, notify);
			}
			
			if (!video.getHasMp3() && video.getHasDownload()) {
				File target = new File(dirMp3Output + "/" + video.getNome() + ".mp3");
				File v = new File(dirVideoOutput + "/" + video.getNome() + ".mp4");
				AudioAttributes audio = new AudioAttributes();
				audio.setCodec("libmp3lame");
				audio.setBitRate(new Integer(128000));
				audio.setChannels(new Integer(2));
				audio.setSamplingRate(new Integer(44100));
				
				EncodingAttributes attrs = new EncodingAttributes();
				attrs.setFormat("mp3");
				attrs.setAudioAttributes(audio);
				
				Encoder encoder = new Encoder();
				try {
					encoder.encode(v, target, attrs);
					video.setHasMp3(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/playlisttomp3/status", method = RequestMethod.GET)
	public ResponseEntity<List<Video>> downloadAndConvert() throws Exception {
		return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param video
	 * @return
	 */
	private Runnable createRunnable(final Video video) {
		return  new Runnable() {
			public void run() {
				VideoInfo i1 = info;
				DownloadInfo i2 = i1.getInfo();
				switch (i1.getState()) {
				case EXTRACTING:
				case EXTRACTING_DONE:
					if (i1 instanceof YoutubeInfo) {
						YoutubeInfo i = (YoutubeInfo) i1;
						System.out.println(i1.getState() + " " + i.getVideoQuality());
					} else if (i1 instanceof VimeoInfo) {
						VimeoInfo i = (VimeoInfo) i1;
						System.out.println(i1.getState() + " " + i.getVideoQuality());
					} else {
						System.out.println("downloading unknown quality");
					}
					break;
				case DONE:
					video.setPorcentagem(new BigDecimal(100));
					break;
				case RETRYING:
					break;
				case DOWNLOADING:
					long now = System.currentTimeMillis();
					if (now - 1000 > last) {
						last = now;
						video.setPorcentagem(new BigDecimal(i2.getCount() / (float) i2.getLength()).multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN));
					}
					break;
				default:
					break;
				}
			}
		};
	}
}

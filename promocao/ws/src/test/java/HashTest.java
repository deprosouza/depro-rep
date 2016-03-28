import br.com.depro.fw.typezero.infrastructure.utils.HashUtil;

public class HashTest {

	public static void main(String[] args) {
		String string = "/tipoMapemanetoTue Oct 20 13:04:30 BRST 2015102wqimkp12rsouzaATIVO";
		System.out.println(HashUtil.getHash((string)));
	}

}

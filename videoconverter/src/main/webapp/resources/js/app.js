var app = angular.module('videoconverter', [ "ui.bootstrap", "ngAnimate" ]);

app.controller('youtubeplaylisttomp3', function($http, $templateCache, $scope, $timeout) {

	$scope.mainCheck = false;
	$scope.youtubeplaylisttomp3Retrieveurls = function() {
		var json = '{';
		json += '"urlPlaylist":"' + $scope.urlPlaylist + '",';
		json += '"videoOutput":"' + $scope.videoOutput + '",';
		json += '"mp3Output":"' + $scope.mp3Output + '"';
		json += "}";

		$http({
			method : 'POST',
			url : '/videoconverter/youtube/playlisttomp3/retrieveurls',
			data : json,
			headers : {
				'Content-Type' : 'application/json'
			},
			cache : $templateCache
		}).success(function(data) {
			$scope.videos = data;
			$(formyoutubeplaylisttomp3DownloadAndConvert).show();
			$scope.mainCheck = false; 
		}).error(function() {
		});
	},

	$scope.youtubeplaylisttomp3DownloadAndConvert = function() {
		$http({
			method : 'POST',
			url : '/videoconverter/youtube/playlisttomp3/downloadandconvert',
			data : $scope.videos,
			headers : {
				'Content-Type' : 'application/json'
			},
			cache : $templateCache
		}).success(function() {
		}).error(function() {
		});
		$timeout($scope.youtubeplaylisttomp3status, 2000);
	}

	$scope.youtubeplaylisttomp3status = function() {
		$http({
			method : 'GET',
			url : '/videoconverter/youtube/playlisttomp3/status',
			headers : {
				'Content-Type' : 'application/json'
			}
		}).success(function(data) {
			$scope.videos = data;
			var hasVideoToDownload = false;
			$(data).each(function() {
				if (this.download || !this.hasMp3) {
					hasVideoToDownload = true;
				}
			});
			
			if (hasVideoToDownload) {
				$timeout($scope.youtubeplaylisttomp3status, 2000);
			}
		}).error(function() {
		});
	}
	
	$scope.checkAll = function() {
		angular.forEach($scope.videos, function(value) {
			if (!value.hasDonwload) {
				value.download = $scope.mainCheck;
			}
		});
	}

});

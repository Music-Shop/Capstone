/**
 * Upload a file to s3
 */

/**

const AWS = require('aws-sdk');
const Busboy = require('busboy');

const BUCKET_NAME = 'musiccapstonetest';
const IAM_USER_KEY = 'AKIA3CVRH2UBMYA3IAL2';
const IAM_USER_SECRET = '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';

function downloadFromS3() {
  let s3bucket = new AWS.S3({
    accessKeyId: IAM_USER_KEY,
    secretAccessKey: IAM_USER_SECRET,
    Bucket: BUCKET_NAME
  });
  s3bucket.createBucket(function () {
      var params = {
        Bucket: BUCKET_NAME,
        Key: 'beat1.mp3',
      };
      s3bucket.getObject(params, function (err, data) {
        if (err) {
          console.log('error in callback');
          console.log(err);
        }
        console.log('success');
        console.log(data);
      });
  });
}

console.log("BUCKETS: " + BUCKET_NAME);
 */

var s3 = require('s3');

//...


 window.AudioContext = window.AudioContext ||     window.webkitAudioContext;
    var context = new AudioContext();

// ...

    function playTunes() {
      var request = new XMLHttpRequest();
      request.open("GET", "https://localhost:8443/audio/", true);
      request.responseType = "arraybuffer";

      spinner.show();

      request.onload = function() {
          spinner.hide();
        var Data = request.response;
        process(Data);
      };

      request.send();
    }

    function process(Data) {
      source = context.createBufferSource(); // Create Sound Source
      context.decodeAudioData(Data, function(buffer) {
        source.buffer = buffer;
        source.connect(context.destination);
        source.start(context.currentTime);
      });
    }


    function stopTunes(){
        if(source.stop){
            source.stop();
        }
    }



/*********************************************************************************************
 * 									Download a file From s3
 *********************************************************************************************/




	const IAM_USER_KEY = 'AKIA3CVRH2UBMYA3IAL2';
	const IAM_USER_SECRET = '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';
	const BUCKET_NAME = 'musiccapstonetest';

	// Set up s3 credentials
	var client = s3.createClient({  
	  s3Options: {
	    accessKeyId: IAM_USER_KEY,
	    secretAccessKey: IAM_USER_SECRET
	  }
	});
	
	 var params = {
	    Bucket: BUCKET_NAME,
	    Key: 'beat1.mp3'
	  };

  	var downloadStream = client.downloadStream(params);

	downloadStream.on('error', function() {
	    res.status(404).send('Not Found');
	  });
	  downloadStream.on('httpHeaders', function(statusCode, headers, resp) {
	    // Set Headers
	    res.set({
	      'Content-Type': headers['content-type']
	    });
	  });
	
	  // Pipe download stream to response
	  downloadStream.pipe(res);
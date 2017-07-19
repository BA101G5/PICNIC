// https://stackoverflow.com/questions/6150289/how-to-convert-image-into-base64-string-using-javascript

function encodeImageFileAsURL(element, callback) {
  var file = element.files[0];
  var reader = new FileReader();
  reader.onloadend = function() {
    // console.log('RESULT', reader.result)
    // alert(callback);
    callback(reader.result);
  }
  reader.readAsDataURL(file);
}

// <input type="file" onchange="encodeImageFileAsURL(this)" />
var onWinResize = function(){
  var numViewportHeight = window.innerHeight;
  var hightLimit = numViewportHeight - 150;
  document.querySelector('#chatroom-list').style.maxHeight = hightLimit + 'px';

  var elAChatroomContainer = document.querySelector('#aChatroom-container');
  var numAChatroomContainerOffsetWidth = elAChatroomContainer.offsetWidth;

  elAChatroomContainer.style.right = numAChatroomContainerOffsetWidth + 'px';

  var jqAChatroomContainerPanelBody = $(elAChatroomContainer).find('.panel-body');
  jqAChatroomContainerPanelBody
      .css('max-height', hightLimit + 'px')
      .scrollTop(jqAChatroomContainerPanelBody[0].scrollHeight);
  
  if(document.documentElement.clientWidth<900){
	  $('#left-navigation-bar').css('display', 'none');
  }else{
	  $('#left-navigation-bar').css('display', 'block');
  }

};
onWinResize();
$( window ).on('resize', onWinResize);
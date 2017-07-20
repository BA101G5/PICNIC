function add_buttonCancel(){
		swal({
			  title: "Are you sure?",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, cancel it!",
			  closeOnConfirm: false
		},
		function(){
				var button_cancel = document.getElementById("button_add_cancel")
				button_cancel.type = "submit";
				button_cancel.click(); 	
		});
	}
	
	function pur_buttonCancel(){
		swal({
			  title: "Are you sure?",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, cancel it!",
			  closeOnConfirm: false
		},
		function(){
				var button_cancel = document.getElementById("button_pur_cancel")
				button_cancel.type = "submit";
				button_cancel.click(); 	
		});
	}
	
	function mod_buttonCancel(){
		swal({
			  title: "Are you sure?",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, cancel it!",
			  closeOnConfirm: false
		},
		function(){
				var button_cancel = document.getElementById("button_mod_cancel")
				button_cancel.type = "submit";
				button_cancel.click(); 	
		});
	}
	
	function kkkk(){
		swal({
			  title: "Are you sure?",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, delete it!",
			  closeOnConfirm: false
		},
		function(){
				var delButton = document.getElementById("del")
				delButton.type = "submit";
			  	delButton.click();  	
		});
	}

	function colorCon(e){
		
		
		   var obj = e? e.target : window.event.srcElement;
		   obj.parentNode.children[0].firstChild.checked="true"
		   var len = obj.parentNode.children.length;
		   
		   var objj = document.getElementsByClassName("colorCon");
		   var objjlen = objj.length;
		   var objjChLen = objj[0].children.length;
		
		   for(var i=0; i<objjlen; i++){
			   for(var j=0;j<objjChLen;j++){
				   objj[i].children[j].style.backgroundColor = "#f5f5f5";
			   }
		   }
		   for( var i=0; i<len; i++){
			   obj.parentNode.children[i].style.backgroundColor = "#E5E5E5";
			}	
		   
	}
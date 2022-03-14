function getHello(){
 $.ajax({ 
      type: "GET",
      url: "http://localhost:8080/HelloWorld/demo/hello",
      success: function(data){        
        $("#resGetHello").html(data);      },
      error:function(res){
        alert("ERROR: "+ res.statusText);  }
  });
}

function getHelloId(myId){
  var myUrl = "http://localhost:8080/HelloWorld/demo/hello/helloId/" + myId;
  $.ajax({ 
     type: "GET",
     url: myUrl,
     success: function(data){        
       $("#resGetHello").html(data);           },
     error:function(res){
           alert("ERROR "+ res.statusText);    }
     });
}

function getDateJSON(){
 $.ajax({ 
    type: "GET",
    dataType: "json",
    url: "http://localhost:8080/HelloWorld/demo/hello/dateJSON",
    success: function(data){        
       var myDate = data.day+"/"+ data.month+"/"+ data.year;
       $("#resGetHello").html("Date received:"+myDate);},
    error:function(res){
     alert("ERROR "+ res.statusText);}
  });

}

function postHelloName(){
    $.ajax({ 
       type: "POST",
       url: "http://localhost:8080/HelloWorld/demo/hello/name",
       contentType: "text/plain",
       dataType: "text",
       data: $("#name").val(),
       success: function(dat){        
          $("#postGetHello").html(dat);       },
       error:function(res){
           alert("ERROR "+ res.statusText);   }
	});
}

function postDateJSON(){
  $.ajax( {
    type:"POST",   
    url:"http://localhost:8080/HelloWorld/demo/hello/myDate2015",
    contentType:"application/json",
    dataType:"json",
    data:JSON.stringify( {"day":$("#day").val(),"month":$("#month").val(), "year":$("#year").val()}),
    success:function(data){
        var newDate = data.day+"/"+data.month+"/"+data.year;
        $("#postDate").html("Date received: "+newDate);    },
    error:function(res){   
		alert("ERROR "+ res.statusText);     }
 }); 
}

function postDateForm(){
  $.ajax( {
    type:"POST",
    url:"http://localhost:8080/HelloWorld/demo/hello/myDateForm",
    contentType:"application/x-www-form-urlencoded",
    dataType:"json",
    data: {

        "day": $("#day").val(),"month": $("#month").val(),   "year":$("#year").val()     },

    success:function(data){
        var newDate = data.day+"/"+data.month+"/"+data.year;
        $("#postDate").html("Date received: "+newDate);    },
    error:function(res){  
		alert("ERROR "+ res.statusText);     }
   });  
}

function putDate(){
	var date=$("#date").val();
  $.ajax( {
    type:"PUT",   
    url:"http://localhost:8080/HelloWorld/demo/hello/modifyDate/" + date,
    contentType:"application/json",
    dataType:"text",
    data:JSON.stringify( {"day":$("#day").val(),"month":$("#month").val(), "year":$("#year").val()}),
    success:function(data){
        $("#putDate").html(data);
		getAllDates()},
    error:function(res){
        alert("ERROR "+ res.statusText);    }
   });  
}

function getAllDates(){
 $.ajax({ 
    type: "GET",
    dataType: "json",
    url: "http://localhost:8080/HelloWorld/demo/hello/allDates",
    success: function(data){   
    var myDate="";
    $.each(data, function( i, value ) {
     myDate = myDate + i+ ": " + value.day+"/"+ value.month+"/"+ value.year + "<br>";    });
       $("#resGetHello").html("Dates: <br>"+myDate);},
    error:function(res){
     alert("ERROR "+ res.statusText);     }
  });   
}

var apikey="";

function getApikey(){
	var user=$("#user").val();
	var passwd=$("#password").val(); 
	$.ajax( {
    type:"POST",   
    url:"http://localhost:8080/HelloWorld/demo/hello/apikey",
    contentType:"application/json",
    dataType:"text",
    data:JSON.stringify( {"user":$("#user").val(),"password":$("#password").val()}),
    success:function(data){
        $("#apikey").html(data); 
		apikey=data;   },
    error:function(res){   
		alert("ERROR "+ res.statusText);     }
 }); 
}

function testApikey2(){
	var user=$("#user").val();
	var passwd=$("#password").val(); 
    $.ajax({ 
       type: "POST",
       url: "http://localhost:8080/HelloWorld/demo/hello/testApikey2",
       headers: {
          	"apikey":apikey,
			"user":user,
			"password":passwd
       },
       dataType: "text",
       success: function(dat){        
          $("#apikey").html(dat);       },
       error:function(res){
           alert("ERROR "+ res.statusText);   }       
});  
}


var mytoken="";

function getJWT(){
	var user=$("#userJWT").val();
	var passwd=$("#passwordJWT").val(); 
    $.ajax({ 
       type: "GET",
       url: "http://localhost:8080/HelloWorld/demo/hello/authenticateJWT",
       headers: {
           "username":user,
           "password":passwd
       },
      dataType: "text",
       success: function(dat){   
           mytoken=dat;
          $("#JWT").html(dat);       
       },
       error:function(res){   
	        alert("ERROR "+ res.statusText);   }       
	});   
}

function testJWT(){
	var user=$("#userJWT").val();
	$.ajax({ 
       type: "POST",
       url: "http://localhost:8080/HelloWorld/demo/hello/testJWT",
       headers: {
      "token":mytoken
       },
       contentType: "text/plain",
       dataType: "text",
       data:user,
       success: function(dat){        
          $("#JWT").html(dat);      },
       error:function(res){
           alert("ERROR "+ res.statusText);   }      
	});
}
	


var mytokenJWE="";

function getJWE(){
	var user=$("#userJWT").val();
	var passwd=$("#passwordJWT").val(); 
    $.ajax({ 
       type: "GET",
       url: "http://localhost:8080/HelloWorld/demo/hello/authenticateJWE",
       headers: {
           "username":user,
           "password":passwd
       },
      dataType: "text",
       success: function(dat){   
           mytokenJWE=dat;
          $("#JWT").html(dat);       
       },
       error:function(res){   
	        alert("ERROR "+ res.statusText);   }       
	});   
}

function testJWE(){
	var user=$("#userJWT").val();
	$.ajax({ 
       type: "POST",
       url: "http://localhost:8080/HelloWorld/demo/hello/testJWE",
       headers: {
      "token":mytokenJWE
       },
       contentType: "text/plain",
       dataType: "text",
       data:user,
       success: function(dat){        
          $("#JWT").html(dat);      },
       error:function(res){
           alert("ERROR "+ res.statusText);   }      
	});
}


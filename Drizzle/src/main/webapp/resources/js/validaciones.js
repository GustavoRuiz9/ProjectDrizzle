
document.getElementById("myBtn").onclick = displayfile;
     
function myFunction() {
	if(document.getElementById("password").value != (document.getElementById("password2")).value){
		alert("password's difrentes");
		return false;
	}else{
		return true;
	}
	
}
     
    
function displayfile() {
    document.getElementById("changeprofile1").click();
    $('#changeprofile1').change( function(event) {
        var tmppath = URL.createObjectURL(event.target.files[0]);
        document.getElementById("Btn").click();
        
        $("img").fadeIn("fast").attr('src',URL.createObjectURL(event.target.files[0]));
        //alert(document.getElementById("Imagen").value);
        
        });
    }
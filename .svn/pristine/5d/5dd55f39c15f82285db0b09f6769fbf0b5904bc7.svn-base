<script>

var map;
var marker = false; 
        
function initMap(coordx,coordy) {
 
 	if (coordx && coordy) {
 		var centerOfMap = new google.maps.LatLng(coordx,coordy);
 	} else {
 		var centerOfMap = new google.maps.LatLng(52.357971, -6.516758);
 	}
 
    //var centerOfMap = new google.maps.LatLng(52.357971, -6.516758);
 
    var options = {
      center: centerOfMap, //Set center.
      zoom: 7 //The zoom value.
    };
 
    map = new google.maps.Map(document.getElementById('map'), options);
 
    google.maps.event.addListener(map, 'click', function(event) {                
        
        var clickedLocation = event.latLng;
       
        if(marker === false){
           
            marker = new google.maps.Marker({
                position: clickedLocation,
                map: map,
                draggable: true //make it draggable
            });
            
            google.maps.event.addListener(marker, 'dragend', function(event){
                markerLocation();
            });
        } else{
            marker.setPosition(clickedLocation);
        }
                
        markerLocation();
    });
}
        
function markerLocation(){

    var currentLocation = marker.getPosition();
    console.log(marker);    
    document.getElementById('location_coord').value = currentLocation.lat() + "," + currentLocation.lng(); //latitude

	$.ajax({
	   url: 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+currentLocation.lat()+','+currentLocation.lng()+'&sensor=true',
	   type: 'GET',
	   error: function(data) {
	      console.log(data);
	   },
	   success: function(data) {
	   	   $('#event_city').val(data.results[1].formatted_address);
	   	   console.log(data.results[1].formatted_address);  
	   }
	});
	

}
</script>
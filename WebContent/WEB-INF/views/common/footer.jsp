

<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<style>

#mark_location {
	display:none;
}
#select_location {
	display:block;
}

</style>
<script>

    $( document ).ready(function() {
    
    	var locations;
    
    	startArora();
    	startAroraList();
    	
    });


	function startAroraList() {
	
		var sel = $('#sel1');
				
		if (sel) {
		
			$.ajax({
			   url: '${contextPath}/list/locations',
			   type: 'GET',
			   error: function(data) {
			      console.log(data);
			   },
			   success: function(data) {
			   		
			   		var obj = $.parseJSON(data);
			   		if (obj.success) {
			   			
			   			locations = obj.locations;
			   			for (var i = 0; i < obj.locations.length; i++) {
			   				sel.append("<option locid='"+obj.locations[i].id+"'>"+obj.locations[i].name+"</option>");
			   			} 			
			   		}
			   }
			});	
			
			
    	
    		$( "#sel1" ).change(function() {
    			var optionSelected = $("option:selected", this);
    			console.log(optionSelected[0].attributes.locid.value);
    			
				console.log(locations);
				
				for (var t = 0; t < locations.length; t++) {
				
					if (locations[t].id == optionSelected[0].attributes.locid.value) {
					
						console.log(locations[t]);
						$('#location_coord').val(locations[t].coordinates);
						console.log($('#event_city'));
						console.log(locations[t].text_location);
						$('#event_city').val(locations[t].text_location);
					}
					
				}
				
			});
    	
    	
    	
		    $("input[name$='location_host']").click(function() {
		        var test = $(this).val();
				if (test === 'mark') {
					$('#mark_location').show();
					$('#select_location').hide();
				}
				if (test === 'exist') {
					$('#mark_location').hide();
					$('#select_location').show();					
				}
		    });
					
		
		}
	
	}


	function startArora() {
		
		var s = $('.num_joined');
		
		for (var i = 0; i < s.length; i++) {
			var eventId = s[i].attributes.eventid.value;
			var eventName = s[i].attributes.eventname.value
			getCountJoined(s[i], eventName, eventId);
		}

	}

	function getCountJoined(elem, eventName, eventId) {
				
		$.ajax({
		   url: '${contextPath}/count/'+ eventName + '/'+ eventId,
		   type: 'GET',
		   error: function(data) {
		      console.log(data);
		   },
		   success: function(data) {
		   		
		   		var obj = $.parseJSON(data);
		   		if (obj.success) {
			   		var res = $(elem).text();
			   		$(elem).html(obj.joined +'/' + res);		   			
		   		}
		   }
		});

		
	}
</script>

</body>
</html>
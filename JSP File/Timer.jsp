<html>
	<head>
		<style>
			.pos
			{
				left: 0;
				margin-top: -100px;
				position: absolute;
				text-align: center;
				top: 50%;
				width: 100%;
			}
			.pos2
			{
				right:0;
				margin-top: -25px;
			}
		</style>
	</head>
	<body>
		<form onsubmit="stopFunction()" action="http://localhost:8080/Results" id="form1" method="post" >
			<table class="pos2" align="right" bgcolor = "#f0f0f0" cellspacing="20">
			<tr>
				<td><h2 align="center">Auction ends in : </h2></td>
			</tr>
			<tr>
				<td><p id="demo" align="right" style="font-size:40px"></p></td>
			</tr>
			<tr>
				<td><center><input type="submit" value="Stop Timer (Demo)"></center></td>
			</tr>
			</table>
		</form>
		<br><br><p class="pos" id="demo2" style="font-size:60px"></p>
		
		<script>
			// Set the date we're counting down to
			var s = "Dec 06, 2020 18:00:00";
			var countDownDate = new Date(s).getTime();
			
			// Update the countdown every 1 second
			var x = setInterval(function()
			{
				// Get today's date and time
				var now = new Date().getTime();
					
				// Find the distance between the countdown date and now
				var distance = countDownDate - now;
					
				// Time calculations for days, hours, minutes and seconds
				var days = Math.floor(distance / (1000 * 60 * 60 * 24));
				var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
				var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
				var seconds = Math.floor((distance % (1000 * 60)) / 1000);
					
				// Output the result in an element with id="demo"
				document.getElementById("demo").innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
					
				// If the count down is over, write some text 
				if (distance < 0)
				{
					clearInterval(x);
					document.getElementById("demo2").innerHTML = "Auction Ended";
					setTimeout(function()
					{
						document.getElementById("form1").submit();
					}, 2000)
				}
			}, 1000);
			

			function stopFunction() 
			{
				alert("Auction Ended!");
			}
		</script>
	</body>
</html>
function openTab(evt, tabName) 
{
  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) 
  {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) 
  {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
}

function fetchAndDisplayRecord() {
  fetch('http://localhost:8180/record')
      .then(response => response.json())
      .then(data => displayRecord(data))
      .catch(error => console.error('Error fetching record:', error));
}

function displayRecord(record) {
  document.getElementById('createdAt').textContent = new Date(record.createdAt).toLocaleString();
  document.getElementById('temperature').textContent = `${record.temperature.toFixed(2)} °C`;
  document.getElementById('windSpeed').textContent = `${record.windSpeed} m/s`;
  //.getElementById('windDirection').textContent = `${record.windDirection} °`;
  //document.getElementById('pressure').textContent = `${record.pressure.toFixed(2)} hPa`;
  document.getElementById('humidity').textContent = `${record.humidity.toFixed(2)}%`;
}

document.addEventListener('DOMContentLoaded', function() {
  fetchAndDisplayRecord();
});
const toggleDropdown = () => {
  const menu = document.getElementById('dropdown');
  menu.style.display = (menu.style.display === 'flex') ? 'none' : 'flex';
}

document.addEventListener('click', function(event) {
  const dropdown = document.getElementById('dropdown');
  const profile = document.querySelector('.profile');
  if (!profile.contains(event.target) && !dropdown.contains(event.target)) {
    dropdown.style.display = 'none';
  }
});

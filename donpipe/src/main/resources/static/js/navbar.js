document.addEventListener("DOMContentLoaded", function() {
    const navbar = document.getElementById('navbar');
    const blueIcon = document.getElementById('left-icon');
    const bronzeIcon = document.getElementById('middle-icon');
    const silverIcon = document.getElementById('right-icon');

    // Define o azul escuro como cor padrão ao carregar a página
    navbar.style.backgroundColor = '#4682B4';

    blueIcon.addEventListener('click', function() {
        navbar.style.backgroundColor = '#4682B4'; // Azul mais escuro
    });

    bronzeIcon.addEventListener('click', function() {
        navbar.style.backgroundColor = '#8C7853'; // Bronze mais escuro
    });

    silverIcon.addEventListener('click', function() {
        navbar.style.backgroundColor = '#808080'; // Cinza mais escuro
    });
});

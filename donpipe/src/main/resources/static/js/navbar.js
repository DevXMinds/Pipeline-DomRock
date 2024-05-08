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
        navbar.style.backgroundColor = '#CD7F32'; // Bronze mais escuro
    });

    silverIcon.addEventListener('click', function() {
        navbar.style.backgroundColor = '#808080'; // Cinza mais escuro
    });
// Captura o parâmetro do URL
    const urlParams = new URLSearchParams(window.location.search);
    const email = urlParams.get('username');

// Verifica se o parâmetro do e-mail existe
    if (email) {

        // Atualiza o texto do elemento HTML "Nome de Usuário" com o e-mail capturado
        document.querySelector('.nav-item #username-button').innerText = email;
    }

    $.ajax({
        type: 'POST',
        url: '/user/login',
        data: JSON.stringify(formData),
        contentType: 'application/json',
        success: function(response) {
            // Atualiza o texto do botão "Nome do Usuário" com o nome de usuário recebido
            usernameButton.text(response.nomeUser); // Supondo que o nome de usuário esteja disponível em response.nomeUser
        },
        error: function(xhr, status, error) {
            console.error('Erro ao fazer login:', error);
        }});
});
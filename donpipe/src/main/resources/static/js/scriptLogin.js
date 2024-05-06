

/**
 * Manipula os eventos de clique nos botões de login e cadastro.
 * 
 * @param {Event} event - O evento de clique.
 */
const handleButtonClick = (event) => {
    formularioCadastro.classList.remove('active');
    formularioLogin.classList.add('active');
    camadaTraseira.style.clipPath = '';
};

/**
 * Manipula os eventos de clique nos botões de cadastro.
 * 
 * @param {Event} event - O evento de clique.
 */
const handleCadastroButtonClick = (event) => {
    formularioLogin.classList.remove('active');
    formularioCadastro.classList.add('active');
    camadaTraseira.style.clipPath = 'inset(0 0 0 50%)';
};

const formularioLogin = document.querySelector('.login-form');
const formularioCadastro = document.querySelector('.cadastro-form'); 
const camadaTraseira = document.querySelector('.back-layer'); 

document.querySelector('.login button').addEventListener('pointerdown', handleButtonClick);

document.querySelector('.cadastro button').addEventListener('pointerdown', handleCadastroButtonClick);

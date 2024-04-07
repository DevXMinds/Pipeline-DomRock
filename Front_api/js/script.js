document.addEventListener('DOMContentLoaded', function () {
    const menuBtn = document.getElementById('menu-btn');
    const menuPanel = document.getElementById('menu-panel');
    const painel = document.getElementById('painel');
    const logoutBtn = document.getElementById('logout-btn');

    menuBtn.addEventListener('click', (event) => {
        event.stopPropagation();
        menuPanel.style.display = (menuPanel.style.display === 'none') ? 'block' : 'none';
    });

    window.addEventListener('click', (event) => {
        if (!menuBtn.contains(event.target) && !menuPanel.contains(event.target)) {
            menuPanel.style.display = 'none';
        }
    });

    document.getElementById('realizar-carga-btn').addEventListener('click', () => {
        painel.style.display = 'block';
    });

    document.getElementById('fechar-painel').addEventListener('click', () => {
        painel.style.display = 'none';
    });

    logoutBtn.addEventListener('click', () => {
        alert('Você fez logout!');
    });
});

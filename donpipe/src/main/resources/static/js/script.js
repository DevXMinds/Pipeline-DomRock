document.addEventListener('DOMContentLoaded', function () {
    const menuBtn = document.getElementById('menu-btn');
    const menuPanel = document.getElementById('menu-panel');
    const painel = document.getElementById('painel');
    const logoutBtn = document.getElementById('logout-btn');
    const fileInput = document.getElementById('file-input');
    const dataTableBody = document.querySelector('#data-table tbody');
    let colunaSelecionada = -1; // Inicializado como -1 para indicar que nenhuma coluna está selecionada

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
        fileInput.click(); 
    });

    fileInput.addEventListener('change', (event) => {
        const file = event.target.files[0];
        const reader = new FileReader();

        reader.onload = (e) => {
            const csvContent = e.target.result;
            preencherPainelComCSV(csvContent);
        };

        reader.readAsText(file);
    });

    function preencherPainelComCSV(csvContent) {
        const linhas = csvContent.split('\n');
        dataTableBody.innerHTML = '';

        const headers = linhas[0].split(';'); // Assume que o cabeçalho está na primeira linha e usa ';' como separador
        const numColunas = headers.length;

        // Adiciona cabeçalhos à tabela
        const headerRow = document.createElement('tr');
        headers.forEach((header, index) => {
            const th = document.createElement('th');
            const icon = document.createElement('span');
            icon.className = "material-icons";
            icon.textContent = 'vpn_key';
            icon.addEventListener('click', () => {
                colunaSelecionada = index; // Armazena o índice da coluna selecionada
                highlightColumnHeaders(); // Chama a função para destacar os cabeçalhos de coluna selecionados
            });
            th.appendChild(icon);
            th.textContent = header;
            headerRow.appendChild(th);
        });
        dataTableBody.appendChild(headerRow);

        // Adiciona dados às linhas da tabela
        for (let i = 1; i < linhas.length; i++) {
            const colunas = linhas[i].split(';');
            if (colunas.length === numColunas) { // Verifica se o número de colunas corresponde ao cabeçalho
                const tr = document.createElement('tr');
                colunas.forEach(coluna => {
                    const td = document.createElement('td');
                    td.textContent = coluna;
                    tr.appendChild(td);
                });
                dataTableBody.appendChild(tr);
            }
        }

        painel.style.display = 'block';
    }

    // Função para destacar os cabeçalhos de coluna selecionados
    function highlightColumnHeaders() {
        const ths = document.querySelectorAll('#data-table th');
        ths.forEach((th, index) => {
            if (index === colunaSelecionada) {
                th.style.backgroundColor = 'yellow';
            } else {
                th.style.backgroundColor = ''; // Remove o destaque dos outros cabeçalhos
            }
        });
    }

    document.getElementById('fechar-painel').addEventListener('click', () => {
        painel.style.display = 'none';
    });

    logoutBtn.addEventListener('click', () => {
        alert('Você fez logout!');
    });

    function converterCSV() {
        var fileInput = document.getElementById('csvFile');
        var file = fileInput.files[0];
        var formData = new FormData();
        formData.append('arquivo', file);
    
        fetch('/load', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById('jsonResult').innerText = JSON.stringify(data, null, 2);
        })
        .catch(error => console.error('Erro:', error));
    } 
});

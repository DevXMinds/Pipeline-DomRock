document.addEventListener('DOMContentLoaded', function () {
    const menuBtn = document.getElementById('menu-btn');
    const menuPanel = document.getElementById('menu-panel');
    const painel = document.getElementById('painel');
    const menuPanelButtons = document.querySelectorAll('#menu-panel button');
    const logoutBtn = document.getElementById('logout-btn');
    const fileInput = document.getElementById('file-input');
    const dataTableBody = document.querySelector('#data-table tbody');
    let colunasSelecionadas = []; // Inicializado como uma lista vazia para armazenar múltiplas colunas selecionadas
    let colunasSelecionadaspk = []; // Inicializado como uma lista vazia para armazenar múltiplas colunas selecionadas PK

    function selecionarLinha(linha) {
        var linhas = document.getElementById("minhaTabela").getElementsByTagName("tr");
        for (var i = 0; i < linhas.length; i++) {
            linhas[i].classList.remove("linha-selecionada");
        }
        linha.classList.add("linha-selecionada");
    }

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

        if (file) {
            reader.onload = (e) => {
                const csvContent = e.target.result;
                preencherPainelComCSV(csvContent);
                const fileName = file.name;
                const fileExtension = fileName.split('.').pop().toLowerCase();
                const rows = csvContent.split('\n');
                const postData = {
                    id: null,
                    idUser: { id: 1 },
                    idEmpresa: { id: 1 },
                    tipoArquivo: fileExtension,
                    dadosArquivo: csvContent,
                    nomeArquivo: fileName,
                    dataCriacao: null,
                    estagio: "lz",
                    estatus: "cancelado",
                    dataModificacao: null
                };

                // Send JSON data to backend
                sendDataToBackend(postData);
            };

            reader.readAsText(file);
        }
    });

    function preencherPainelComCSV(csvContent) {
        const linhas = csvContent.split('\n');
        dataTableBody.innerHTML = '';

        const headers = linhas[0].split(';');
        const numColunas = headers.length;

        // Adiciona cabeçalhos à tabela
        const headerRow = document.createElement('tr');
        headers.forEach((header, index) => {
            const th = document.createElement('th');
            const button = document.createElement('button');
            const icon = document.createElement('i'); // Create an icon element
            button.className = "header-button";
            button.appendChild(icon);
            icon.className = 'material-icons'; // Add a class for icon styling
            icon.textContent = 'vpn_key'; // Set the icon text
            th.textContent = header;
            th.appendChild(button);
            button.addEventListener('contextmenu', function(event) {
                event.preventDefault();
            });

            button.addEventListener('click', () => {
                event.stopPropagation();
                selecionarPk(index,button);


            });
            th.addEventListener('click',()=>{
                selecionarColunas(index)
            })
            headerRow.appendChild(th);
        });
        dataTableBody.appendChild(headerRow);

        // Adiciona dados às linhas da tabela
        for (let i = 1; i < linhas.length; i++) {
            const colunas = linhas[i].split(';');
            if (colunas.length === numColunas) {
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
// Função para destacar as colunas selecionadas na tabela
    function highlightSelectedColumns() {
        const ths = document.querySelectorAll('#data-table th');
        ths.forEach((th, index) => {
            if (colunasSelecionadas.includes(index)) {
                th.classList.add('selecionada');
            } else {
                th.classList.remove('selecionada');
            }
        });
    }

// Event listeners para seleção de colunas ao clicar nos cabeçalhos
    document.querySelectorAll('#data-table th').forEach((th, index) => {
        th.addEventListener('click', () => {

            selecionarColunas(index);
        });
    });


    function selecionarPk(index, button) {
        const columnIndex = colunasSelecionadaspk.indexOf(index);
        if (columnIndex === -1) {

            colunasSelecionadaspk = [];
            // If the clicked column is not already selected, add it to the selected columns
            colunasSelecionadaspk.push(index);
        } else {
            // If the clicked column is already selected, remove it from the selected columns
            colunasSelecionadaspk = [];
        }

        // Remove the 'toggled' class from all buttons
        document.querySelectorAll('.header-button').forEach(btn => {
            btn.classList.remove('toggled');
        });

        // Add the 'toggled' class to the clicked button
        button.classList.add('toggled');
    }
    function selecionarColunas(index) {
        const columnIndex = colunasSelecionadas.indexOf(index);
        if (columnIndex === -1) {
            colunasSelecionadas.push(index);
        } else {
            colunasSelecionadas.splice(columnIndex, 1)
        }
        highlightSelectedColumns();
    }
    // Função para gerar JSON com as colunas selecionadas    TA ENVIANDO OS DADOS JSON PRO CONSOLE
    function gerarJSONColunaSelecionada(tipo) {
        if (colunasSelecionadas.length > 0) {
            const headers = document.querySelectorAll('#data-table th');


            const colunasSelecionadasNomespK = colunasSelecionadaspk.map(index =>{
                const columnHeader = headers[index].textContent.trim();
                return columnHeader.replace('vpn_key', '').trim();
        });
            const colunasSelecionadasNomes = colunasSelecionadas.map(index => {
                const columnHeader = headers[index].textContent.trim();
                return columnHeader.replace('vpn_key', '').trim();
            });
            const notdeletableValue = colunasSelecionadasNomes.join(";");
            //console.log(colunasSelecionadasNomes,colunasSelecionadasNomespK);

            const postData =
            {
                id: null,
                idArquivo: {id: null},
                data: null,
                colunaPk: colunasSelecionadasNomespK[0],
                notdeletable: notdeletableValue
            }
            sendLZtoBackEnd(postData);


        } else {
            alert('Por favor, selecione uma ou mais colunas antes de prosseguir.');
        }
    }
    function sendLZtoBackEnd(data){
        fetch('/lz/load', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Data sent successfully');
                    // Optionally, perform actions on successful data upload
                } else {
                    console.error('Failed to send data');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }


    function showPopupInativo() {
        alert('Função não implementada');
    }
    menuPanelButtons.forEach(button => {
        button.addEventListener('click', showPopupInativo);
    });

    document.querySelector('.botao-enviar').addEventListener('click', () => {
        gerarJSONColunaSelecionada('PK');
    });

    document.querySelector('.botao-coluna-nao-deletavel').addEventListener('click', () => {
        gerarJSONColunaSelecionada('Not Deletable');
    });

    document.getElementById('fechar-painel').addEventListener('click', () => {
        painel.style.display = 'none';
        console.log = ("eae");
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

function sendDataToBackend(data) {
    // You can use fetch or any other method to send data to the backend
    fetch('/arquivo/load', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                console.log('Data sent successfully');
                // Optionally, perform actions on successful data upload
            } else {
                console.error('Failed to send data');
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

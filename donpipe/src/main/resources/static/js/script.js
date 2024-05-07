document.addEventListener('DOMContentLoaded', function () {
    const menuBtn = document.getElementById('open-popup-btn');
    const menuPanel = document.getElementById('menu-panel');
    const painel = document.getElementById('painel');
    const menuPanelButtons = document.querySelectorAll('.menu-panel button');
    const logoutBtn = document.getElementById('logout-btn');
    const fileInput = document.getElementById('csvFile');
    const dataTableBody = document.querySelector('#data-table tbody');
    let colunasSelecionadas = [];
    let colunasSelecionadaspk = [];

    function selecionarLinha(linha) {
        var linhas = document.getElementById("data-table").getElementsByTagName("tr");
        for (var i = 0; i < linhas.length; i++) {
            linhas[i].classList.remove("linha-selecionada");
        }
        linha.classList.add("linha-selecionada");
    }

    menuBtn.addEventListener('click', (event) => {
        event.stopPropagation();
        document.getElementById('popup-container').style.display = 'block';
    });

    window.addEventListener('click', (event) => {
        if (!menuBtn.contains(event.target) && !menuPanel.contains(event.target) && !event.target.closest('#popup-container')) {
            document.getElementById('popup-container').style.display = 'none';
        }
    });

    document.getElementById('close-popup-btn').addEventListener('click', () => {
        document.getElementById('popup-container').style.display = 'none';
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
                const postData = {
                    id: null,
                    idUser: { id: 1 },
                    idEmpresa: { id: 1 },
                    nomeUpload: document.getElementById('nomeUpload').value,
                    header: document.getElementById('header').checked,
                    delimiter: document.getElementById('delimiter').value,
                    tipoArquivo: fileExtension,
                    dadosArquivo: csvContent,
                    nomeArquivo: fileName,
                    dataCriacao: null,
                    estagio: "lz",
                    estatus: "cancelado",
                    dataModificacao: null
                };
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

        const headerRow = document.createElement('tr');
        headers.forEach((header, index) => {
            const th = document.createElement('th');
            th.textContent = header;
            headerRow.appendChild(th);
        });
        dataTableBody.appendChild(headerRow);

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

    function gerarJSONColunaSelecionada(tipo) {
        if (colunasSelecionadas.length > 0) {
            const headers = document.querySelectorAll('#data-table th');
            const colunasSelecionadasNomespK = colunasSelecionadaspk.map(index => {
                const columnHeader = headers[index].textContent.trim();
                return columnHeader.replace('vpn_key', '').trim();
            });
            const colunasSelecionadasNomes = colunasSelecionadas.map(index => {
                const columnHeader = headers[index].textContent.trim();
                return columnHeader.replace('vpn_key', '').trim();
            });
            const notdeletableValue = colunasSelecionadasNomes.join(";");
            const postData = {
                id: null,
                idArquivo: { id: null },
                data: null,
                colunaPk: colunasSelecionadasNomespK[0],
                notdeletable: notdeletableValue
            }
            sendLZtoBackEnd(postData);
        } else {
            alert('Por favor, selecione uma ou mais colunas antes de prosseguir.');
        }
    }

    function sendLZtoBackEnd(data) {
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

    document.querySelector('#send-btn').addEventListener('click', () => {
        gerarJSONColunaSelecionada('PK');
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

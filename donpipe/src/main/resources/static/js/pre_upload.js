document.getElementById('open-popup-btn').addEventListener('click', function() {
    document.getElementById('popup-container').style.display = 'block';
});

document.getElementById('close-popup-btn').addEventListener('click', function() {
    document.getElementById('popup-container').style.display = 'none';
});

document.getElementById('send-btn').addEventListener('click', function() {
    var nomeUploadValue = document.getElementById('nomeUpload').value; // Variável para o nome do upload
    var checkboxValue = document.getElementById('header').checked; // Variável para o valor do checkbox
    var delimiterValue = document.getElementById('delimiter').value; // Variável para o valor do delimitador selecionado

    console.log(nomeUploadValue); // Você pode substituir console.log pelas ações que deseja realizar com os valores
    console.log(checkboxValue);
    console.log(delimiterValue);
});

document.getElementById('csvForm').addEventListener('submit', function (event) {
    event.preventDefault();

    document.getElementById('output').innerHTML = '';

    var fileInput = document.getElementById('csvFile');
    var file = fileInput.files[0];
    if (!file) {
        alert('Por favor, selecione um arquivo CSV.');
        return;
    }

    var nomeUpload = document.getElementById('nomeUpload').value;

    var reader = new FileReader();
    reader.onload = function (event) {
        var csvData = event.target.result;

        var delimiter = detectDelimiter(csvData);
        if (!delimiter) {
            alert('Não foi possível detectar o delimitador padrão do CSV.');
            return;
        }

        var userDelimiter = document.getElementById('delimiter').value;

        var modifiedCsvData = csvData.replace(new RegExp(escapeRegExp(delimiter), 'g'), userDelimiter);

        var lines = modifiedCsvData.split('\n');

        var output = document.getElementById('output');
        lines.forEach(function (line) {
            var rowDiv = document.createElement('div');
            rowDiv.textContent = line;
            output.appendChild(rowDiv);
        });
    };

    reader.readAsText(file);

    document.getElementById('popup-container').style.display = 'none';
});

function detectDelimiter(csvData) {
    var delimiters = [',', ';', '|'];
    for (var i = 0; i < delimiters.length; i++) {
        if (csvData.indexOf(delimiters[i]) !== -1) {
            return delimiters[i];
        }
    }
    return null;
}

function removeHeader(csvData) {
    var lines = csvData.split('\n');
    lines.shift(); // Remove a primeira linha (header)
    return lines.join('\n');
}

function escapeRegExp(string) {
    return string.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
}

document.getElementById('csvFile').addEventListener('change', function(event) {
    const fileInput = event.target;
    const file = fileInput.files[0];
    if (file) {
        const fileSize = file.size;
        const fileSizeFormatted = formatFileSize(fileSize);
        document.getElementById('file-size').textContent = ' (' + fileSizeFormatted + ')';
    } else {
        document.getElementById('file-size').textContent = '';
    }
});

function formatFileSize(bytes) {
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i];
}

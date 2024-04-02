function converterCSV() {
    var fileInput = document.getElementById('csvFile');
    var file = fileInput.files[0];
    var formData = new FormData();
    formData.append('arquivo', file);

    fetch('/converter', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('jsonResult').innerText = JSON.stringify(data, null, 2);
    })
    .catch(error => console.error('Erro:', error));
}

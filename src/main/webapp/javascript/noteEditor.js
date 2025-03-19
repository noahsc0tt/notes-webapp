// This file contains functions for inserting links and images into the note editor

function insertTextAtCursor(template) {
    const textarea = document.getElementsByName("body")[0];
    const startPosition = textarea.selectionStart;
    const endPosition = textarea.selectionEnd;
    const beforeText = textarea.value.substring(0, startPosition);
    const afterText = textarea.value.substring(endPosition, textarea.value.length);

    textarea.value = beforeText + template + afterText;
    textarea.focus();
}

function insertLink() {
    const url = prompt("Enter URL:", "https://");
    const text = prompt("Enter link text:", "");

    if (url && text) { insertTextAtCursor("[" + text + "](" + url + ")"); }
}

function insertImage() { document.getElementById('imageUpload').click(); }

function handleImageUpload() {
    const fileInput = document.getElementById('imageUpload');
    const file = fileInput.files[0];
    const label = prompt("Enter image label for searching:", file.name);
    if (file) { insertTextAtCursor("![" + label + "](img/" + file.name + ")"); }
}

// Add event listener for file upload
document.addEventListener('DOMContentLoaded', function() {
    const imageUpload = document.getElementById('imageUpload');
    if (imageUpload) {
        imageUpload.addEventListener('change', handleImageUpload);
    }
});
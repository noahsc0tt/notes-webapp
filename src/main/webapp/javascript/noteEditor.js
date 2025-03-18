function insertLink() {
    const url = prompt("Enter URL:", "https://");
    const text = prompt("Enter link text:", "Link Text");
    if (url && text) {
        const textarea = document.getElementsByName("body")[0];
        const startPos = textarea.selectionStart;
        const endPos = textarea.selectionEnd;
        const beforeText = textarea.value.substring(0, startPos);
        const afterText = textarea.value.substring(endPos, textarea.value.length);
        textarea.value = beforeText + "[" + text + "](" + url + ")" + afterText;
        textarea.focus();
    }
}

function insertImage() {
    document.getElementById('imageUpload').click();
}

function handleImageUpload() {
    const fileInput = document.getElementById('imageUpload');
    const file = fileInput.files[0];

    if (file) {
        const textarea = document.getElementsByName("body")[0];
        const startPos = textarea.selectionStart;
        const endPos = textarea.selectionEnd;
        const beforeText = textarea.value.substring(0, startPos);
        const afterText = textarea.value.substring(endPos, textarea.value.length);

        // Insert markdown for the image
        textarea.value = beforeText + "![" + file.name + "](img/" + file.name + ")" + afterText;
        textarea.focus();
    }
}

// Add event listeners after DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    const imageUpload = document.getElementById('imageUpload');
    if (imageUpload) {
        imageUpload.addEventListener('change', handleImageUpload);
    }
});
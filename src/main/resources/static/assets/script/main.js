


const btnEdits = document.querySelectorAll(".btn-edit");

btnEdits.forEach(element => {
    element.addEventListener("click", handleEdit);
});

function handleEdit(e){
    e.preventDefault();
    const id = this.dataset.id;
    imageUrl = this.dataset.image;
    description = document.querySelector(`#description${id}`).innerHTML;
    brandName = document.querySelector(`#product-name${id}`).innerHTML
    document.querySelector("#edit-product-name").value = brandName.trim();
    document.querySelector("#edit-image-url").value = imageUrl;
    document.querySelector("#edit-message-text").value = description.replace(/(\n)|" "+/g, " ").trim();
    document.querySelector("#edit-form").action = `edit/product/${id}`;

}

btnEdits.addEventListener("click", handleEdit);
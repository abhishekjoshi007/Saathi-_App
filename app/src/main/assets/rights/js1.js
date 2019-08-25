var accordions = document.querySelectorAll("button.accordion");
for (var i = 0; i < accordions.length; i++) {
  accordions[i].onclick = function() {
    closeAll(this);
    this.classList.toggle("active");
    this.nextElementSibling.classList.toggle("show");
  };
}

function closeAll(thisButton) {
  for (var i = 0; i < accordions.length; i++) {
      if(accordions[i]!==thisButton)
      {
    accordions[i].classList.remove("active");
    accordions[i].nextElementSibling.classList.remove("show");
    }  }
}
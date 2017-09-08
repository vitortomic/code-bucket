[].slice.call(document.querySelectorAll('.unfollow-text')).forEach(function(button) {button.click();});

//ES6 version
let nodeList = document.querySelectorAll('.unfollow-text')
let arr = Array.from(nodeList);
arr.forEach((element)=>element.click());

Array.from(document.querySelectorAll('.unfollow-text')).forEach((element)=>element.click());

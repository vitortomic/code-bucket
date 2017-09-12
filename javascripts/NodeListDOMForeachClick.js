[].slice.call(document.querySelectorAll('.unfollow-text')).forEach(function(button) {button.click();});
//es5 node list to array
//var arr = Array.prototype.slice.call(document.querySelectorAll('.unfollow-text'));
//arr.forEach(function(element){element.click();});

//ES6 version
let nodeList = document.querySelectorAll('.unfollow-text')
let array = Array.from(nodeList);
array.forEach((element)=>element.click());

Array.from(document.querySelectorAll('.unfollow-text')).forEach((element)=>element.click());

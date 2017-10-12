fetch('https://jsonplaceholder.typicode.com/posts')
  .then(response=>response.json())
  .then((json)=>console.log(json))


fetch('https://jsonplaceholder.typicode.com/posts')
.then(response=>response.json())
.then((json)=>{
    var result = json.filter((element)=>element.id<10).map((element)=> element.title).join(", ");
    console.log(result)
})

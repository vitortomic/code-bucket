let delayFor = (delay) => new Promise(resolve => 
	setTimeout(()=>resolve(),delay)
)
delayFor(4000).then(completed=>console.log("completed"))

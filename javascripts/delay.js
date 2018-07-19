const delayFor = (delay) => new Promise(resolve => 
	setTimeout(()=>resolve(),delay)
)

//delayFor(4000).then(completed=>console.log("completed"))

const delayedLoop = async (iterations, delay) => {
	for (let i = 0; i < iterations; i++) {
		await delayFor(delay)
		console.log(`Iteration ${i}`)
	}
}

delayedLoop(10, 1000)

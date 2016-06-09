fizzBuzz <- function(number) {
	if(number %% 5 == 0 && number %% 3 == 0)
	 return('fizzBuzz')
	if(number %% 3 == 0)
	 return('fizz')
	if(number %% 5 == 0)
	 return('buzz')

	number	
}

applyFizzBuzz <- function(array) {
  res = lapply(array, fizzBuzz)
	for(stuff in res) {
		cat (stuff)
		cat (' ')
	}
  
  res
}

applyFizzBuzz(x<-1:100)

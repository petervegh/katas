test.should_return_fizz_for_3 <- function() {
  expected = list('fizz')
  checkEquals(expected, applyFizzBuzz(array<-3:3))
}

test.should_return_fizz_for_6 <- function() {
  expected = list('fizz')
  checkEquals(expected, applyFizzBuzz(array<-6:6))
}

test.should_return_buzz_for_5 <- function() {
  expected = list('buzz')
  checkEquals(expected, applyFizzBuzz(array<-5:5))
}

test.should_return_buzz_for_10 <- function() {
  expected = list('buzz')
  checkEquals(expected, applyFizzBuzz(array<-10:10))
}

test.should_return_1_for_1 <- function() {
  expected = list(1)
  checkEquals(expected, applyFizzBuzz(array<-1:1))
}

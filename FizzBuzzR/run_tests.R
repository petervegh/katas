library('RUnit')

source('fizzbuzz.R')

test.suite <- defineTestSuite("fizzbuzz",
                             dirs = file.path("tests"),
                             testFileRegexp = '^\\d+\\.R')

test.result <- runTestSuite(test.suite)

printTextProtocol(test.result)
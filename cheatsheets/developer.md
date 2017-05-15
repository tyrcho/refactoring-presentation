# Developer Cheatsheet

_Just a few hints on what to do from the source code..._

_Don't forget to execute the tests all the time! (Hello infinitest!)_

1. Come back to a **stable state**: comment the tests which are failing.
1. Rename `baseNumber` to what it actually does... _(converting units btw)_
1. Extract the part of `toRomanNumeral` dealing with the tens... _(use your IDE for that)_
1. See how similar it should be to `convertUnits`... _(refactor in that way)_
1. See that you actually miss a part about hundreds... And see that it (once again) is the same as other conversions... _(it is time for some generic code)_
1. Refactor `convertTens` and `convertUnits` to use that `convertGeneric` that you wrote...
1. And then implement a `convertHundreds` method which uses the generic one.
1. Simplify the `toRomanNumeral` method to rely on the `convert` methods... You can get rid of recursion at this point...

**Well done! You fixed the bug in production!**

_Let's continue then..._

1. Reorganize the architecture of the code: extract the `EnglishNumberToWords` to another file,
1. Then extract all the roman conversion methods to a new class,
1. Things need to be named properly... Rename the main program, and bring some consistency in the names of the converters...
1. Refactor the code in `displayNumbers` to use the `Files` API, just like the other methods...
1. See how similar they are! Only the function in the middle is called!
1. Refactor to have only one method with a function in parameter...
1. Modify the source code to call that one method each time with the correct function.

**The code should be better now... But displaying all the numbers, then all words, then all roman...**

**Also, it isn't really testable...**

1. Create a new method which will display a list of String...
1. Refactor the conversion method to return a list of String, obtained by applying the function in parameter to all numbers read.
1. Now you can test all functions unitary if you want...
1. See that all converters share the same "contract", and could extend a common abstract class.
1. Refactor all converters in that way... Create a converter for simply creating a String from a number.
1. Register all converters in a list in your batch.
1. Modify the conversion function to call all the converters for each number read.

**And now it works as a Stream :)**
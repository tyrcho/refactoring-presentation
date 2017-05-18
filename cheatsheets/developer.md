# Phase 0: discovery

1. Show the code structure: classes/tests/files.
2. Execute the batch: locate the bug.
3. Show the main class: locate the method with the bug.
4. Show the tests: execute the `Roman` tests.
5. Show Infinitest.

# Phase 1: refactoring then bugfix

**Start Presenter Mode.**

1. Rename `baseNumber` in `convertUnits`
2. Extract `convertTens`
3. Use `convertTens` in `toRomanNumeral`
4. Replace `null` with `""`
5. Replace recursive call to `toRomanNumeral` with `convertUnits(i%10)`
6. Show similarities between `convertTens` and `convertUnits`
7. Duplicate `convertUnits` in `convertGeneric`
8. Add arguments for `one`, `five`, `ten`
9. Replace `convertUnits` implementation with a call to `convertGeneric`
10. Replace `convertTens` implementation with a call to `convertTens`
11. Update the `convertTens` call with `i/10`
12. Remove noise in `toRomanNumeral`
13. Check that `testHundreds` still fails
14. Implement `convertHundreds` with a call to `convertGeneric(i,"C","L","M")`
15. Update `toRomanNumeral` to return `convertHundreds(i/100) + toRomanNumeral(i%100)`
16. Execute the tests
17. Replace `L` from `D` to fix the bug

**Production bug is solved!**

# Phase 2: boyscout refactoring

1. Execute and show `testBatch`
2. Move class `EnglishNumbers` to another file
3. Move members (alt-7, F6) to `io.techforum.refactoring.RomanConverter`
4. Hide private members in `RomanConverter`
5. Rename the batch to `NumbersConversionBatch`
6. Show that all `display` methods are duplicated
7. Highlight the only difference: the function which is used for conversion
8. Duplicate `displayRomanNumbers` and call it `convertAndWriteNumbers`
9. Add a `Function<Integer,String>` parameter to that method and use it
10. Refactor all the `display` methods to call `convertAndWriteNumbers` with a lambda
11. Inline all the `display` methods (which are badly named)

**We can stop here!**

# Phase 3: summay

1. Show the code structure
2. Show the main class

**Go back to the slides**
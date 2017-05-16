1. run batch
1. explain batch tests
1. show convert roman code

# Phase 1 : roman numerals

1. explain unit test, Infinitest
1. baseNumber => convertUnits
1. extract convertTens
1. return directly convertTens in convertRoman
1. replace null with ""
1. replace call to convertRoman => convertUnits
1. replace by i % 10
1. pull up calls to convertUnits(i % 10)
1. duplicate convertUnits => convertGeneric, add arguments
1. convertUnits calls convertGeneric
1. duplicate convertUnits to convertTens2
1. try to call convertTens2(i)
1. try to call convertTens2(i/10)
1. remove convertTens
1. rename convertTens2
1. remove noise in convertRoman
1. check that test still fails for 456
1. return to stable state
1. write convertHundreds as convertGeneric(i, "C", "L", "M");
1. return convertHundreds(i / 100) + toRomanNumeral(i % 100)
1. uncomment tests
1. fix bug L => D

# Phase 2 : Batch

1. uncomment test for batch
1. move class EnglishNumbers
1. move members (alt-7, F6) to io.techforum.refactoring.RomanConverter
1. hide private members
1. rename to NumbersConversionBatch
1. detect that 3 displayXXXmethods are not DRY
1. public interface Converter {       String convert(int number);   }
1. duplicate displayRomanNumbers => convertAndWriteNumbers(Converter converter)
1. displayRomanNumbers calls it with anonymous impl
1. IDE : replace with lambda
1. displayHumanNumbers : duplicate of lambda
1. displayNumbers : idem number + ""
1. change signature to convertAndWriteNumbers(Function<Integer, String> converter)
1. remove interface Converter
1. inline the 3 methods display ... (badly named)
1. replace lambda with method reference x2
1. could now continue with better exception handling
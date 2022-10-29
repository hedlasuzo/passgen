# passgen

Password generator, written in Java, build with Maven

Current features:

- Generates some passwords of different lengths and character ranges
- Calculates entropy of the parameters used to generate the password
- Custom patterns such as "1 ucase 1 lcase 14 urlsafe 8 digit"
- Checks the generated passwords using
  the [Pwned Passwords API from Have I Been Pwned](https://haveibeenpwned.com/API/v3#PwnedPasswords).
# Terminal

The program simulates a simple terminal. Coursework for Olio-Ohjelmoinnin perusteet 2, spring 2019. Unfortunately the program and its comments are in Finnish, because it was a requirement for the course.

## Install

Clone the repository and run in command prompt/terminal
```
javac harjoitustyo/Oope2HT.java
java harjoitustyo/Oope2HT
```

## Docker 

```
docker build -t terminal .
docker run -it  terminal
```

## Commands

- md &lt;name&gt;
    - Creates a directory with &lt;name&gt;
- mf &lt;name&gt; &lt;size&gt;
    - Creates a file with &lt;name&gt; and &lt;size&gt;
- cd &lt;name&gt;
    - Navigates to directory by &lt;name&gt;, if possible
- ls &lt;name&gt;
    - Lists contents of current directory if no &lt;name&gt; is given
    - If &lt;name&gt; is given, prints out information of a corresponding directory or file
- find
    - Recursively lists content of the current directory and its sub-directories
- mv &lt;original&gt; &lt;new&gt;
    - Renames &lt;original&gt; file or directory to &lt;new&gt;
- cp &lt;filename&gt; &lt;new&gt;
    - Creates a copy of file &lt;filename&gt; with the name of &lt;new&gt;
- rm &lt;name&gt;
    - Removes a corresponding directory or file with &lt;name&gt;
- exit
    - Terminates the program

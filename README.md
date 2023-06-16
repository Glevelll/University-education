# University-education

# Machines

# [Non-deterministic State Machine](https://github.com/Glevelll/University-education/tree/main/Machines/Machine)

This section discusses the operation of a specific non-deterministic state machine.
![image](https://user-images.githubusercontent.com/113721736/215865928-63f2a50b-5661-44fb-a447-85c467c338de.png)
As we can see, it has 2 input symbols, 5 output symbols and 4 states. Since any transition of such an automaton is uniquely determined by the current state and the input symbol, where reading the input symbol is required for each state change, then passing through each state we will, if necessary, replace the input symbols with the output ones, and we will transition through the states according to the conditions dictated by our machine


# [Turing Machine](https://github.com/Glevelll/University-education/tree/main/Machines/Turing)

This project demonstrates the operation of a Turing machine that looks like this:
![image](https://user-images.githubusercontent.com/113721736/215868756-600dc010-c97d-4eb2-9c67-c1f73518ae68.png)
There is an alphabet of 5 characters, 3 states and a table itself showing the operation of the machine in the direction of the left, right, or stay in place.
A strategy similar to a non-deterministic finite automaton: replacing characters one by one, followed by a transition that the machine dictates.

[Regular Expression](https://github.com/Glevelll/University-education/tree/main/Machines/Regular)

In this case, a random regular expression is generated from the characters specified in the code, which in turn is written to the file. To create a valid regular expression, the program considers many cases that must be met before the program creates the final regular expression. This is where the obvious problem comes in: we are sacrificing program execution time. But since the purpose of this section is to illustrate that the programmer is familiar with the concept of a regular expression, I ask the reader to close their eyes to this little nuisance :)



# [Spring Core](https://github.com/Glevelll/University-education/tree/main/SprngAntns)
Spring Core - это фреймворк для разработки на Java, который дает возможность разработки приложения как набора слабосвязанных компонентов. Чем меньше компоненты приложения знают друг о друге, тем проще разрабатывать новый и поддерживать существующий функционал приложения. Spring состоит из множества аннотаций, которые облегчают работу с Java.

В файле sprngAnts можно найти реализацию Spring Core. В одной из папок, под анологичным названием реализация Spring аннотационно применяется к классам, которые формируют систему, содержащую различные жанры кино, а также имеется 3 варианта реализации проигрывания кино: FilmPlayerWithConstructor, FilmPlayerWithSetter и, самый минималистичный, FilmPlayer.

В том же файле можно найти папку Facade, которая содержит в себе кинотеатр, строящийся на паттерне Фасад (см. https://github.com/Glevelll/AdapterAndFacade), включающий в себя Spring Core и соответствующие аннотации. В этом же файле есть папка Adapter, где также реализован Spring и плюс к этому применён @Bean. @Bean отличается тем, что он прелставляет собой singleton, а созданием экземпляров и установкой в бины зависимостей управляет контейнер фреймворка Spring.

Также, как дополнительный материал на рассмотрение, был взят Google Guice.

Guice устраняет потребность в фабриках и использовании new в коде Java. В некоторых случаях приходится писать фабрики, но код не будет напрямую зависеть от них. Guice позволяет легче изменять, тестировать и повторно использовать код в других контекстах.

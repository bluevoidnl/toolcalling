# toolcalling

Simple example to use the first step of toolcalling: 

1. convert the prompt to parameters
2. call your own api using the params from the prompt
3. optionally feed the resulting json to the LLM to do extra filtering usoing the same initial prompt

This example also shows how the first step can be used for text classification: 
translating a prompt to a choice of one of the food types that are mentioned in the prompt.

Extracting stations names or times are done in the same way.

It is also possible to provide multiple tools and let the LLM determine which tool to use.

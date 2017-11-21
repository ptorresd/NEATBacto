# NEATBacto

Este es un proyecto hecho en java 8 y usando la IDE eclipse. El package neatBacto contiene el modelo del juego y las clases de jugadores, que corresponde a lo que programe para esta tarea. El resto de los archivos son parte del paquete JNEAT.

Para correr pruebas en la gui hay que correr el archivo MainGui.java ubicado en el package gui.

Una vez abierta la gui, en la pestaña "jneat parameter", se pueden modificar los parametros de la red siguiendo los siguientes pasos:
1) Apretar el boton "Load default".
2) Modificar el valor del parametro que se quiera cambiar y apretar Enter.
3) Apretar el boton "Write"

Luego para modificar la cantidad de epochs de la simulación hay que ir a la pestaña "session parameter" y seguir los siguientes pasos:
1) Apretar el boton "Load sess default".
2) Modificar el valor al lado de epoch.
3) Apretar el boton "Write sess"

Finalmente, para correr la simulación hay que ir a la pestaña "start simulation" y apretar el boton "start". Una vez iniciada la simulacion hay dos formas de visualizarla, con el output de texto o un grafico del comportamiento de la población y se pueden cambiar en las opciones de la izquierda (la opcion "graph champion" fue desactivada).

sub0: ; cuenta binaria de 4/5
	cbr r16, 0xFF ; resetemos el registro a 0
	ori r16, 4 ; primer numero de la cuenta

loop_0:
	mov r18, r16 ; Hacemos una copia en otro registro para no perder el valor original

	andi r19, 0 ;multiplicamos el registro por 0 para limpiarlo
	ori r19, 0b1111_0000 ; hacemos la mascara del puerto d 
	and r19, r18 ; mmultiplicar por registro para poner la capa
	out DDRD, r19 ; sacamos los valores del puerto d
	out PORTD, r19

	andi r19, 0 ;volvemos a multiplicar el registro y limpiarlo
	ori r19, 0b0000_1111 ; creamos ahora la mascara que vamos a usar para el puerto B
	and r19, r18 ; multiplicamos los registros para aplicar la mascara creada anteriormente
	out DDRB, r19 ; sacamos por el puerto B los valores correspondientes
	out PORTB, r19
	call delay_1s ; llamamos a la funcion de delay_1s

	cpi r16, 5 ;hacemos una comparacion con un numero (5), para saber cuando terminar la subrutina
	breq loop ; si la comparacion es igual, volvemos al bucle principal
	inc r16  ; si el valor es distinto incrementamos r16 
	rjmp loop_0 ;y volvemos a hacer el bucle de la subrutina hasta que llegue al valor que queremos
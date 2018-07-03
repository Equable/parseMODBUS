<h1><b>Parse MODBUS Protocol</b></h1>

<h3><b>Author: Louis Huerta-Blake</b></h3>
<h4><b>README Last Edited: 7/2/2018</b><h4>

<h2><b><u>Summary</u></b></h2>

>Parses hexcode from the MODBUS protocol log generated by the prosoft card into readable table that states the line, id, function code and all other parameters assosciated with the code.

<h2><b><u>How It Works</u></b></h2>

>coming soon


<h2><b><u>How To Use</u></b></h2>

> Rename the file path to the working path of your hexcode.txt file to use this

<h2><b><u>Looks</u></b></h2>

![New UI](https://raw.githubusercontent.com/Equable/parseMODBUS/master/hexcode%20parse.PNG)


![Select File via Browse](https://raw.githubusercontent.com/Equable/parseMODBUS/master/hexcode%20parse%20browse.PNG)

<h2><b><u>Dependencies</u></b></h2>

>* google guava v23.0


<h2><b><u>Version</u></b></h2>

>* 1.1 File Select
>   * Can now select file on application run, no longer need to manually change path in code
>   * Due to JDK 8, JFileChooser appears very tiny on high dpi screens. Will look into JavaFX at a later date.
>* 1.0 Parses all requests within the hexcode.txt file based on their function code
>   * only affects the [] values. <> are requests
>   * ability to to parse responses will be next followed by filtering


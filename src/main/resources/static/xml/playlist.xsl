<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>La mejor música</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th>Grupo</th>
      <th>Título</th>
    </tr>
	<xsl:for-each select="playlist/cancion">
        <tr>
          <td><xsl:value-of select="grupo"/></td>
          <td><xsl:value-of select="titulo"/></td>
        </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>
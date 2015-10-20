<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:barcode="org.krysalis.barcode4j.xalan.BarcodeExt" xmlns:common="http://exslt.org/common"
	xmlns:xalan="http://xml.apache.org" exclude-result-prefixes="barcode common xalan">
	<xsl:template match="bookingDetails">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4-portrait" page-height="29.7cm" page-width="21cm" margin-top="0.5cm" margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
					
					<fo:region-body margin-top="0.5cm"/>
					<fo:region-before extent="0cm"/>
					<fo:region-after extent="1cm"/>
                       
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="A4-portrait">
				<fo:flow flow-name="xsl-region-body">
			  		<fo:block space-after.optimum="5pt">
			  		<fo:table width="100%"  >
							<fo:table-column column-number="1" column-width="55%"/>
							<fo:table-column column-number="2" column-width="12%"/>
							<fo:table-column column-number="3" column-width="33%"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell>
									<fo:block>
									<fo:table width="100%"  border-color="Wheat" border-bottom-width="1pt" border-bottom-style="solid">
										<fo:table-column column-number="1" column-width="50%"/>
										<fo:table-column column-number="2" column-width="50%"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell>
													<fo:block>
														<fo:external-graphic src="url('pdfheader/casa_logo.png')" content-height="scale-to-fit" height="0.70in"  content-width="0.70in" scaling="non-uniform"/>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="right">
														<xsl:for-each select="./agent">
														<fo:block font-family="Arial" font-size="10pt" font-weight="normal">
															<xsl:value-of select="name" />
														</fo:block>
														<fo:block font-family="Arial" font-size="10pt" font-weight="normal">
															<xsl:value-of select="email" />
														</fo:block>
														<fo:block font-family="Arial" font-size="10pt" font-weight="normal">
															<xsl:value-of select="phone" />
														</fo:block>
														</xsl:for-each>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
									
									</fo:block>
									<fo:block>
										<fo:table width="100%"  >
											<fo:table-column column-number="1" column-width="5%"/>
											<fo:table-column column-number="2" column-width="95%"/>
											<fo:table-body>
												<fo:table-row>
													<fo:table-cell height="1.5cm">
														<fo:block></fo:block>
													</fo:table-cell>
													<fo:table-cell text-align="left" height="1.5cm" display-align="after">
														<xsl:for-each select="./customers/customer">
															<fo:block font-family="Arial" font-size="9pt" font-weight="normal">
																<xsl:value-of select="title"/>
																<fo:character character=" " suppress-at-line-break="retain"/>
																<xsl:value-of select="lastName"/>
																<fo:character character=" " suppress-at-line-break="retain"/>
																<xsl:value-of select="middleName"/>
																<fo:character character=" " suppress-at-line-break="retain"/>
																<xsl:value-of select="firstName"/>
															</fo:block>
														</xsl:for-each>
													</fo:table-cell>
												</fo:table-row>
											</fo:table-body>
										</fo:table>
									</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
									<fo:block font-size="10pt" font-weight="bold" font-family="sans-serif" color="GoldenRod">
									</fo:block>
									</fo:table-cell>
									<fo:table-cell>
									<fo:block font-size="8pt" font-weight="bold" font-family="sans-serif" line-height="11pt" space-after.optimum="2pt" background-color="brown" color="white" text-align="left" padding-top="1pt">
										PRO-FORMA INVOICE
									</fo:block>
									<fo:block background-color="WhiteSmoke">
										<fo:table width="100%"  >
											<fo:table-column column-number="1" column-width="5%"/>
											<fo:table-column column-number="2" column-width="45%"/>
											<fo:table-column column-number="3" column-width="45%"/>
											<fo:table-column column-number="4" column-width="5%"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell height="0.5cm" border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid"><fo:block></fo:block></fo:table-cell>
												<fo:table-cell height="0.5cm" border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid"><fo:block></fo:block></fo:table-cell>
												<fo:table-cell height="0.5cm" border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid"><fo:block></fo:block></fo:table-cell>
												<fo:table-cell height="0.5cm" border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid"><fo:block></fo:block></fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
												<fo:table-cell text-align="left" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal">Date</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="right" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal"><xsl:value-of select="./bookingDate" /></fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
											</fo:table-row>
											<xsl:for-each select="./agent">
											<fo:table-row>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
												<fo:table-cell text-align="left" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal">Agent</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="right" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal"><xsl:value-of select="name" /></fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
											</fo:table-row>
											</xsl:for-each>
											<xsl:for-each select="./agent">
											<fo:table-row>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
												<fo:table-cell text-align="left" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal">Agent Code</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="right" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal"><xsl:value-of select="code" /></fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
											</fo:table-row>
											</xsl:for-each>
											<xsl:for-each select="./payment">
											<fo:table-row>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
												<fo:table-cell text-align="left" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal">Price</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="right" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal">$<xsl:value-of select="fare" /></fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
											</fo:table-row>
											</xsl:for-each>
											<xsl:for-each select="./payment">
											<fo:table-row>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
												<fo:table-cell text-align="left" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal">Tax</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="right" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal">$<xsl:value-of select="tax" /></fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
											</fo:table-row>
											</xsl:for-each>
											<xsl:for-each select="./payment">
											<fo:table-row>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
												<fo:table-cell text-align="left" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="bold">Invoice Total</fo:block>
												</fo:table-cell>
												<fo:table-cell text-align="right" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="bold">$<xsl:value-of select="total" /></fo:block>
												</fo:table-cell>
												<fo:table-cell><fo:block></fo:block></fo:table-cell>
											</fo:table-row>
											</xsl:for-each>
											<xsl:for-each select="./payment">
											<fo:table-row>
												<fo:table-cell border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid"><fo:block></fo:block></fo:table-cell>
												<fo:table-cell border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid" text-align="left" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal">Payment Type</fo:block>
												</fo:table-cell>
												<fo:table-cell border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid" text-align="right" height="0.4cm">
													<fo:block font-family="Arial" font-size="9pt" font-weight="normal"><xsl:value-of select="type" /></fo:block>
												</fo:table-cell>
												<fo:table-cell border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid"><fo:block></fo:block></fo:table-cell>
											</fo:table-row>
											</xsl:for-each>
										</fo:table-body>
										</fo:table>
									</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
					</fo:table>
			  			
			  		</fo:block>

					<fo:block font-size="8pt" font-weight="bold" font-family="sans-serif" line-height="11pt" space-after.optimum="10pt" background-color="brown" color="white" text-align="left" padding-top="1pt">
					FLIGHT INFORMATION
					</fo:block>
					<fo:block background-color="WhiteSmoke" space-after.optimum="10pt">
					<fo:table width="100%"  >
							<fo:table-column column-number="1" column-width="75%" />
							<fo:table-column column-number="2" column-width="25%" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="left">
									<fo:block>
										<fo:table width="100%"  >
											<fo:table-column column-number="1" column-width="55%" />
											<fo:table-column column-number="2" column-width="37%" />
											<fo:table-column column-number="3" column-width="8%" />
											<fo:table-body>
												<fo:table-row>
													<fo:table-cell text-align="left">
														<fo:block font-family="Arial" font-size="9pt" font-weight="bold" background-color="GoldenRod" line-height="20pt">Travelers:</fo:block>
													</fo:table-cell>
													<fo:table-cell text-align="left">
														<fo:block font-family="Arial" font-size="9pt" font-weight="bold" background-color="GoldenRod" line-height="20pt">Status:</fo:block>
													</fo:table-cell>
													<fo:table-cell text-align="left">
														<fo:block font-family="Arial" font-size="9pt" font-weight="normal" line-height="20pt"></fo:block>
													</fo:table-cell>
												</fo:table-row>
												<xsl:variable name="status">
													<xsl:value-of select="./bookingStatus"/>
												</xsl:variable>
												<xsl:for-each select="./customers/customer">
													<fo:table-row>
														<fo:table-cell text-align="left">
															<fo:block font-family="Arial" font-size="9pt" font-weight="normal" background-color="White" line-height="20pt">
																<xsl:value-of select="title"/>
																<fo:character character=" " suppress-at-line-break="retain"/>
																<xsl:value-of select="lastName"/>
																<fo:character character=" " suppress-at-line-break="retain"/>
																<xsl:value-of select="middleName"/>
																<fo:character character=" " suppress-at-line-break="retain"/>
																<xsl:value-of select="firstName"/>
															</fo:block>
														</fo:table-cell>
														<fo:table-cell text-align="left">
															<fo:block font-family="Arial" font-size="9pt" font-weight="normal" background-color="White" line-height="20pt">
																<xsl:copy-of select="$status" />
															</fo:block>
														</fo:table-cell>
														<fo:table-cell text-align="left">
															<fo:block font-family="Arial" font-size="9pt" font-weight="normal" line-height="20pt"></fo:block>
														</fo:table-cell>
													</fo:table-row>
												</xsl:for-each>
											</fo:table-body>
										</fo:table>
									</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block>
											<fo:table width="100%"  >
											<fo:table-column column-number="1"/>
												<fo:table-body>
													<fo:table-row>
														<fo:table-cell text-align="center">
															<fo:block font-family="Arial" font-size="9pt" font-weight="bold" background-color="GoldenRod" line-height="20pt">Airline Confirmation #:</fo:block>
														</fo:table-cell>
													</fo:table-row>
													<fo:table-row>
														<fo:table-cell text-align="center">
															<fo:block font-family="Arial" font-size="9pt" font-weight="bold" background-color="White" line-height="20pt">
																<xsl:value-of select="./pnr"/>
															</fo:block>
														</fo:table-cell>
													</fo:table-row>
												</fo:table-body>
											</fo:table>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell>
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
					</fo:table>
					<fo:table width="100%"  >
							<fo:table-column column-number="1" column-width="15%" />
							<fo:table-column column-number="2" column-width="20%" />
							<fo:table-column column-number="3" column-width="30%" />
							<fo:table-column column-number="4" column-width="20%" />
							<fo:table-column column-number="5" column-width="15%" />
							<fo:table-body>
								<fo:table-row  background-color="GoldenRod">
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" font-weight="bold" line-height="20pt">Date:</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" font-weight="bold" line-height="20pt">Departure:</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" font-weight="bold" line-height="20pt">Arrival:</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" font-weight="bold" line-height="20pt">Flight Details:</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" font-weight="bold" line-height="20pt">Seat(s):</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:for-each select="./flights/flight">
										<fo:table-row border-color="white" border-bottom-width="0.5pt" border-bottom-style="solid">
											<fo:table-cell text-align="left">
												<fo:block font-family="Arial" font-size="9pt" line-height="20pt">
																<xsl:value-of select="departureDate"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left">
												<fo:block font-family="Arial" font-size="9pt" line-height="20pt">
																<xsl:value-of select="departureTime"/>
												</fo:block>
												<fo:block font-family="Arial" font-size="9pt" line-height="20pt">
																<xsl:value-of select="departurePlace"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left">
												<fo:block font-family="Arial" font-size="9pt" line-height="20pt">
																<xsl:value-of select="arrivalTime"/>
												</fo:block>
												<fo:block font-family="Arial" font-size="9pt" line-height="20pt">
																<xsl:value-of select="arrivalPlace"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left">
												<fo:block font-family="Arial" font-size="9pt" line-height="20pt">
																<xsl:value-of select="airlineName"/>
												</fo:block>
												<fo:block font-family="Arial" font-size="9pt" line-height="20pt">
																<xsl:value-of select="airlineConfNum"/>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell text-align="left">
												<fo:block font-family="Arial" font-size="9pt" line-height="20pt">
																<xsl:value-of select="seats"/>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
								</xsl:for-each>
							</fo:table-body>
					</fo:table>
					</fo:block>
					<fo:block font-size="8pt" font-weight="bold" font-family="sans-serif" line-height="11pt" space-after.optimum="10pt" background-color="brown" color="white" text-align="left" padding-top="1pt">
					BEFORE YOU GO
					</fo:block>
					<fo:block>
						<fo:table width="100%"  >
							<fo:table-column column-number="1" />
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="left" border-color="brown" border-bottom-width="0.5pt" border-bottom-style="solid" border-top-width="0.5pt" border-top-style="solid">
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* Online check-in may be available.
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* Reconfirm your flights within 24 hours of flight time.
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* Make sure you have all necessary identity and travel documents.
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* Check immigration requirements for the countries you are traveling to.
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
					</fo:block>
					
					<fo:block page-break-inside="avoid">
      					<fo:block font-size="8pt" font-weight="bold" font-family="sans-serif" line-height="11pt" space-after.optimum="10pt" background-color="brown" color="white" text-align="left" padding-top="1pt">
						Terms and Conditions
						</fo:block>
						<fo:block>
						<fo:table width="100%"  >
							<fo:table-column column-number="1" column-width="5%"/>
							<fo:table-column column-number="2" column-width="95%"/>
							<fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="left" border-color="brown" border-top-width="0.5pt" border-top-style="solid">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left" border-color="brown" border-top-width="0.5pt" border-top-style="solid">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt" font-weight="bold">
											Fare Rules
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt">
											*IMPORTANT ADVISORY REGARDING INTERNATIONAL TRAVEL* ENTRY REQUIREMENTS FOR EACH FOREIGN DESTINATION MAY VARY. A
passport is required for international air travel. A travel visa, and/or proof of vaccination(s) such as Yellow Fever may also be required. Passengers are
urged to verify documentation requirements prior to purchasing airfare.
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt">
											*Tickets are non-refundable/non-transferable. Passengers must verify flight information 24 hours before departure. On Domestic flights passengers must
arrive to the airport 2 hours prior to departure and must have a valid government photo ID. On international flights passengers are required to arrive 3
hours prior to flights and must have a valid passport along with a valid visa if necessary. It is the passengers responsibility to verify if their destination
country requires a visa. There is an exchange penalty plus fare difference if a ticket date is required to be changed. A ticket date exchange must be made
before departure date. The store/agency were ticket was purchased shall not be responsible for any errors as it is the passengers responsibility to have
all of their required travel documents in order and up to date. Passenger must verify names, dates, times, at the time the reservation receipt is printed as
the store/agency will not be held responsible. *LOS BOLETOS AEREOS SON NO REEMBOLSABLES/NO TRANSFERIBLES RECONFIRMAR VUELOS
24 HRS ANTES DE LA SALIDA.EN VUELOS DOMESTICOS PRESENTARSE EN EL AEROPUERTO 2 HORAS ANTES DEL VUELO Y LLEVAR
IDENTIFICACION CON FOTO.EN VUELOS INTERNACIONALES PRESENTARSE EN EL AEROPUERTO 3 HORAS ANTES DEL VUELO , VERIFICAR
VISA PARA VUELOS INTERNACIONALES CARGOS ADICIONALES POR CAMBIOS EN LA RESERVACION. LOS BOLETOS AEREOS NO SON
ENDOSABLES, NI REEMBOLSABLES. UNA PENALIDAD POR CAMBIOS DE FECHAS, MAS DIFERENCIA DE PRECIOS. LOS CAMBIOS DEBEN
HACERSE ANTES DE LA SALIDA O DE LO CONTRARIO SU ITINERARIO Y BOLETO NO TENDRA VALIDEZ MONETARIA ALGUNA, ESTA ES UNA
REGLA MUY IMPORTANTE Y SIN FLEXIBILIDAD . ES RESPONSABILIDAD DEL PASAJERO LLEVAR EN ORDEN SUS DOCUMENTOS DE
VIAJE:PASAPORTES, VISAS,PERMISOS DE VIAJE U OTROS DOCUMENTOS QUE PUDIERAN SER REQUERIDOS POR LA(S) AEROLINEA(S) Y/O
POR OFICIALES DE INMIGRACION. VERIFIQUE NOMBRE(S), FECHA(S), HORARIO(S), SERVICIOS ESPECIALES, DESPUES DE EMITIDO EL
BOLETO, LA AGENCIA NO SE HACE RESPONSABLE POR NINGUN ERROR.
										</fo:block>
									</fo:table-cell>
								</fo:table-row>	
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell >
									<fo:table-cell text-align="left" border-color="Wheat" border-top-width="0.5pt" border-top-style="solid">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt" font-weight="bold">
											International Travel
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt">
											Travelers with international itineraries are obliged to ascertain the specific entry, transit, and exit requirements, as well as visitor stay limits, for each
foreign destination included in their itinerary. Details are available from the consulate of each country. US Citizens may also find destination information
online at http://travel.state.gov. Airlines may deny boarding to passengers who do not possess required travel documentation upon checking in for an
international flight itinerary. Documentation may include but is not limited to:
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* A passport for each passenger, valid well beyond the foreseen trip end date
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* A travel visa, issued in advance by a consulate of the destination country, if required
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* Proof of onward or return travel, within the applicable visa stay time limit
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* Proof of vaccinations, such as Yellow Fever
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="15pt">
											* Documentation related to specific passengers or cargo, such as pets, or children not traveling with both parents
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt">
											Some government-mandated fees, such as country entry/exit taxes, may be collected at airports and/or border crossings.
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt" font-weight="bold">
											Flight Reconfirmation
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt">
											Airlines may adjust flight numbers and schedules without advance notice. Always contact the airline(s) directly prior to each flight departure to reconfirm
reservations, and verify timing.
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt" font-weight="bold">
											Airline Check-In
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt">
										Passengers checking in late may be denied boarding. Allow as much as 4 hours prior to international, and 2 hours prior to domestic flight departure times
for airport check in, and/or to check luggage if checking in online. Allow adequate time at transfer points, especially at international arrivals points and/or
when separate check in, luggage retrieval and/or transfer between carriers may be necessary.
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt" font-weight="bold">
											Baggage
										</fo:block>
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt">
										Baggage allowances vary, depending on carrier, itinerary, aircraft and season. Baggage fees may apply. Baggage transfer agreements between carriers
in a given itinerary may or may not exist. Consult the airline(s) website, or phone directly to verify baggage details, if applicable.
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block font-family="Arial" font-size="9pt" line-height="10pt"></fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block line-height="20pt">&#160;</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>
						</fo:table>
						
						<fo:table width="100%" border-color="Wheat" border-top-width="1pt" border-top-style="solid" >
										<fo:table-column column-number="1" column-width="50%"/>
										<fo:table-column column-number="2" column-width="50%"/>
										<fo:table-body>
											<fo:table-row>
												<fo:table-cell text-align="left" display-align="after">
														<xsl:for-each select="./agent">
															<fo:block font-family="Arial" font-size="9pt" font-weight="normal">
																<xsl:value-of select="name" />
															</fo:block>
															<fo:block font-family="Arial" font-size="9pt" font-weight="normal">
																<xsl:value-of select="email" />
															</fo:block>
															<fo:block font-family="Arial" font-size="9pt" font-weight="normal">
																<xsl:value-of select="phone" />
															</fo:block>
														</xsl:for-each>
												</fo:table-cell>
												<fo:table-cell text-align="right">
													<fo:block>
														<fo:external-graphic src="url('pdfheader/casa_logo.png')" content-height="scale-to-fit" height="0.70in"  content-width="0.70in" scaling="non-uniform"/>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
						</fo:table>
									
									
						</fo:block>
   					</fo:block>
					<fo:block page-break-inside="avoid">
      					<fo:block font-size="8pt" font-weight="bold" font-family="sans-serif" line-height="11pt" space-after.optimum="10pt" background-color="brown" color="white" text-align="left" padding-top="1pt">
						Offers
						</fo:block>
						<fo:block font-size="14pt" font-weight="bold" font-family="sans-serif" color="black" text-align="left">
							Avail 5% Discount in all South India Casa Outlets
						</fo:block>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>
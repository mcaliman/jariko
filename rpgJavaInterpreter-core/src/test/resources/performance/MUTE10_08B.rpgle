   COP* *NOUI
     V*=====================================================================
     V* MODIFICHE Ril.  T Au Descrizione
     V* gg/mm/aa  nn.mm i xx Breve descrizione
     V*=====================================================================
     V* 16/12/19  001378  BMA   Creato
     V*=====================================================================
     D* OBIETTIVO
     D*  Programma finalizzato ai test performance sulla CALL
     V*---------------------------------------------------------------------
      * Considerare i seguenti codici operativi
      *+----------+--+---------!--+
      *!RPGLE     !ST!BUILT-IN !ST!
      *+-------------+ --------!--+
      *!CALL      !  !         !  !
      *+----------+--+---------+--+
     D $TIMST          S               Z   INZ
     D $TIMEN          S               Z   INZ
     D $TIMMS          S             10  0
     D $CICL           S              7  0
      * Main
     C                   EXSR      F_CALL
      *
    MU* Type="NOXMI"
    MU* TIMEOUT(0900)
     C                   SETON                                        LR
      *---------------------------------------------------------------------
    RD* Routine test SORTA
      *---------------------------------------------------------------------
     C     F_CALL        BEGSR
      * Start Time
     C                   TIME                    $TIMST
      * Varable for loop
     C                   EVAL      $CICL=100000
      * Call
     C                   CALL      'MUTE10_08'
     C                   PARM                    $CICL
      * End time
     C                   TIME                    $TIMEN
      * Elapsed time
     C     $TIMEN        SUBDUR    $TIMST        $TIMMS:*MS
     C                   EVAL      $TIMMS=$TIMMS/1000
      *
     C                   ENDSR

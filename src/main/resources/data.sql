
INSERT INTO InputData (field1, field2, field3, field4, field5, refkey1, refkey2)
VALUES
    ('Value1', 'Value2', 'Value3', 'Value4', 10.5, 'RefKey1', 'RefKey2'),
    ('Value5', 'Value6', 'Value7', 'Value8', 20.5, 'RefKey3', 'RefKey4');


INSERT INTO ReferenceData (refkey1, refdata1, refkey2, refdata2, refdata3, refdata4)
VALUES
    ('RefKey1', 'RefData1', 'RefKey2', 'RefData2', 'RefData3', 15.5),
    ('RefKey3', 'RefData4', 'RefKey4', 'RefData5', 'RefData6', 25.5);

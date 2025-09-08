package ru.aston.homework.modul5;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.homework.modul5.adapter.Adapter;
import ru.aston.homework.modul5.adapter.LegacySystem;

@ExtendWith(MockitoExtension.class)
class AdapterTest {

    @Mock
    private LegacySystem legacySystem;

    @InjectMocks
    private Adapter adapter;

    @Test
    void testHandleRequestSuccess() {
        String input = "example data";
        String expectedResult = "processed example data";

        when(legacySystem.processData(input))
                .thenReturn(expectedResult);

        String result = adapter.handleRequest(input);

        assertEquals(expectedResult, result);
        verify(legacySystem).processData(input);
    }

    @Test
    void testHandleRequestNullInput() {
        String input = "safe default value";
        String expectedResult = "processed safe value";

        when(legacySystem.processData(input))
                .thenReturn(expectedResult);

        String result = adapter.handleRequest(input);

        assertEquals(expectedResult, result);
        verify(legacySystem).processData(input);
    }

    @Test
    void testHandleRequestEmptyInput() {
        String input = "";
        String expectedResult = "empty input processed";

        when(legacySystem.processData(""))
                .thenReturn(expectedResult);

        String result = adapter.handleRequest(input);

        assertEquals(expectedResult, result);
        verify(legacySystem).processData("");
    }
}

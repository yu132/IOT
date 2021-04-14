
import { consts } from './consts';

function getRandomColorIndex () {
    const { colors } = consts;
    const colorCount = colors.length;
    const colorIndex = Math.floor(Math.random() * colorCount);
    return colorIndex;
}

function getRandomBoolean () {
    return Math.random() < 0.5;
}

export {
    getRandomColorIndex,
    getRandomBoolean
};

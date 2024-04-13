package ir.amirreza.shazam

import android.annotation.SuppressLint
import android.graphics.RectF
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.amirreza.shazam.ui.theme.ShazamTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val StartColor = Color(0xFF57BDF8)
val EndColor = Color(0xFF3579F4)


val SplashColor = Color(0xFF3272F2)

val BoxColor = Color(0xFF73B9F8)


class MainActivity : ComponentActivity() {
    private val ins = MutableInteractionSource()
    private var isStartChanged by mutableStateOf(true)

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isWentToScreen2 by remember {
                mutableStateOf(false)
            }
            val scaleScreenChanged by animateFloatAsState(
                targetValue = if (isWentToScreen2) 0.8f else 1f,
                animationSpec = tween(1000), label = ""
            )
            val weightScreenChanged by animateFloatAsState(
                targetValue = if (isWentToScreen2) 0.4f else .8f,
                animationSpec = tween(1000), label = ""
            )
            val fraction = remember {
                Animatable(1f)
            }
            val iconAlpha = remember {
                Animatable(1f)
            }
            val boxAnimationOffset = remember {
                Animatable(0f)
            }
            val color = remember {
                Animatable(Color(0xFF3373F5))
            }
            val boxColor = remember {
                Animatable(Color.White)
            }
            val scale = remember {
                Animatable(1f)
            }
            val screenAlpha = remember {
                Animatable(0f)
            }
            val screen2Alpha = remember {
                Animatable(0f)
            }
            val sBackgroundColor = remember {
                Animatable(SplashColor)
            }
            val eBackgroundColor = remember {
                Animatable(SplashColor)
            }
            val isClicked by ins.collectIsPressedAsState()
            var isLineEqualizerPlay by remember {
                mutableStateOf(true)
            }
            LaunchedEffect(key1 = Unit) {
                launch {
                    sBackgroundColor.animateTo(StartColor, tween(2000))
                }
                launch {
                    eBackgroundColor.animateTo(EndColor, tween(2000))
                }
                launch {
                    fraction.animateTo(0f, tween(2000))
                }
                launch {
                    launch {
                        delay(1750)
                        iconAlpha.animateTo(0f, tween(200))
                    }
                    delay(2100)
                    launch {
                        iconAlpha.animateTo(1f, tween(200))
                    }
                    launch {
                        fraction.animateTo(1f, tween(1000))
                    }
                }
                boxAnimationOffset.animateTo(1f, tween(1500))
                launch {
                    color.animateTo(Color.White, tween(2000))
                }
                launch {
                    boxColor.animateTo(BoxColor, tween(2000))
                }
                delay(1000)
                launch {
                    screenAlpha.animateTo(1f)
                }
            }
            LaunchedEffect(key1 = isClicked, key2 = isWentToScreen2) {
                if (isWentToScreen2.not()) {
                    if (isClicked) {
                        scale.animateTo(0.9f, tween(500))
                    } else {
                        if (scale.value >= 1f) {
                            delay(2000 + 1600)
                        }
                        while (true) {
                            scale.animateTo(1.05f, tween(1000))
                            scale.animateTo(1f, tween(1000))
                        }
                    }
                }
            }
            LaunchedEffect(key1 = isWentToScreen2) {
                if (isStartChanged.not()) {
                    if (isWentToScreen2) {
                        isLineEqualizerPlay = true
                        screenAlpha.animateTo(0f, tween(200))
                        screen2Alpha.animateTo(1f, tween(200))
                        delay(5000)
                        isLineEqualizerPlay = false
                    } else {
                        screen2Alpha.animateTo(0f, tween(200))
                        screenAlpha.animateTo(1f, tween(200))
                    }
                }
                isStartChanged = false
            }
            ShazamTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        sBackgroundColor.value,
                                        eBackgroundColor.value,
                                    )
                                )
                            )
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .padding(horizontal = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .alpha(screenAlpha.value)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Rounded.ArrowBack,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                    Text(text = "Back", color = Color.White, fontSize = 12.sp)
                                }
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(Color.White.copy(0.3f))
                                            .size(8.dp)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(Color.White)
                                            .size(8.dp)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(Color.White.copy(0.3f))
                                            .size(8.dp)
                                    )
                                }
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        imageVector = Icons.Rounded.Menu,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                    Text(text = "Menu", color = Color.White, fontSize = 12.sp)
                                }
                            }
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = null,
                                modifier = Modifier.alpha(screen2Alpha.value)
                            )
                        }
                        Spacer(modifier = Modifier.weight(weightScreenChanged))
                        AnimatedVisibility(
                            visible = isWentToScreen2.not(),
                            enter = fadeIn(),
                            exit = fadeOut(),
                        ) {
                            AnimatedText(
                                text = if (isClicked) {
                                    "Auto Shazam is on"
                                } else "Tap to Shazam",
                                Modifier.alpha(screenAlpha.value)
                            )
                        }
                        BoxWithConstraints(
                            modifier = Modifier
                                .padding(top = 36.dp)
                                .height(300.dp)
                                .size(200.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            val offset =
                                maxHeight.minus(200.dp) * (1 - boxAnimationOffset.value)
                            Box(
                                modifier = Modifier
                                    .offset(y = offset)
                                    .scale(scale.value.coerceAtLeast(1f))
                                    .scale(scaleScreenChanged)
                            ) {
                                EqualizerCircles(isClicked && isWentToScreen2.not())
                                if (isWentToScreen2) {
                                    EqualizerCircles2(Modifier.alpha(screen2Alpha.value))
                                    EqualizerCirclesWithBorder(Modifier.alpha(screen2Alpha.value))
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(200.dp)
                                        .background(boxColor.value)
                                        .drawBehind {
                                            drawRect(
                                                Color.Black.copy(0.4f),
                                                blendMode = BlendMode.Overlay
                                            )
                                            drawArc(
                                                Brush.radialGradient(
                                                    colorStops = arrayOf(
                                                        .9f to Color.Transparent,
                                                        1f to Color.Black.copy(0.05f)
                                                    ),
                                                ),
                                                -45f,
                                                -90f,
                                                useCenter = true
                                            )
                                            drawArc(
                                                Brush.radialGradient(
                                                    colorStops = arrayOf(
                                                        .95f to Color.Transparent,
                                                        1f to Color.Black.copy(0.2f)
                                                    ),
                                                ),
                                                0f,
                                                360f,
                                                useCenter = true
                                            )
                                            drawRect(
                                                Brush.verticalGradient(
                                                    colors = listOf(
                                                        Color.Black.copy(0.1f),
                                                        Color.Transparent
                                                    ),
                                                    endY = size.height * .05f
                                                ),
                                                size = Size(size.width, size.height * .08f)
                                            )
                                        }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .scale(scale.value.coerceAtMost(1f))
                                            .clip(CircleShape)
                                            .background(boxColor.value)
                                            .fillMaxSize()
                                            .combinedClickable(ins, null, onLongClick = {}) {
                                                isWentToScreen2 = !isWentToScreen2
                                            }
                                            .drawWithContent {
                                                drawContent()
                                                drawArc(
                                                    Brush.verticalGradient(
                                                        listOf(
                                                            Color.White,
                                                            Color.Transparent
                                                        ),
                                                        endY = size.height.div(2)
                                                    ),
                                                    sweepAngle = -180f,
                                                    startAngle = 0f,
                                                    useCenter = false,
                                                    style = Stroke(2f)
                                                )
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Canvas(modifier = Modifier.fillMaxSize(0.4f)) {
                                            translate(
                                                top = -4.dp.toPx(),
                                                left = -2.dp.toPx()
                                            ) {
                                                rotate(-45f) {
                                                    getPaths(
                                                        fraction.value,
                                                        size
                                                    ).forEach {
                                                        drawPath(
                                                            it,
                                                            color.value.copy(iconAlpha.value),
                                                            style = Stroke(
                                                                22.dp.toPx(),
                                                                cap = StrokeCap.Round
                                                            ),
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Box(contentAlignment = Alignment.TopCenter) {
                            Box(
                                modifier = Modifier
                                    .alpha(screenAlpha.value)
                                    .padding(top = 8.dp)
                                    .clip(CircleShape)
                                    .background(BoxColor)
                                    .size(56.dp)
                                    .drawWithContent {
                                        drawContent()
                                        drawArc(
                                            Brush.verticalGradient(
                                                listOf(
                                                    Color.White,
                                                    Color.Transparent
                                                )
                                            ),
                                            sweepAngle = -180f,
                                            startAngle = 0f,
                                            useCenter = false,
                                            style = Stroke(2f)
                                        )
                                    }, contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(screen2Alpha.value),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                LineEqualize(isLineEqualizerPlay, Modifier)
                                AnimatedText(
                                    text = if (isLineEqualizerPlay) "Listen for music" else "Searching for match",
                                    modifier = Modifier.padding(top = 12.dp)
                                )
                                AnimatedText(
                                    text = if (isLineEqualizerPlay) "Make sure your device can hear the song clearly" else "Please wait",
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .alpha(0.6f),
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


fun getPaths(
    fraction: Float,
    size: Size
): List<Path> {
    val iconSize = 20
    val rightPath = Path()
    rightPath.moveTo(5f, 15f.div(2))
    rightPath.lineTo(15f, 15f.div(2))
    rightPath.arcTo(
        Rect(
            Offset(15f, 15f.div(2) + 15f.div(2).plus(15).minus(15f.div(2)).div(2f)),
            radius = 15f.div(2).plus(15).minus(15f.div(2)).div(2f)
        ),
        -90f,
        180f,
        false
    )
    rightPath.lineTo(7f, 15f.div(2).plus(15))
    val leftPath = Path()
    leftPath.moveTo(13f, 0f)
    leftPath.lineTo(5f, 0f)
    leftPath.arcTo(
        Rect(
            Offset(5f, 15f / 2f),
            15f / 2f
        ),
        -90f,
        -180f,
        false
    )
    leftPath.lineTo(15f, 15f)
    rightPath.addRect(size.toRect())

    val matrix = Matrix()
    matrix.scale(size.width / iconSize, size.height / iconSize)
    rightPath.transform(matrix)
    leftPath.transform(matrix)
    return listOf(leftPath.getSegment(fraction), rightPath.getSegment(fraction))
}

fun Path.getSize(): Pair<Float, Float> {
    val rect = RectF()
    this.asAndroidPath().computeBounds(rect, true)
    return Pair(rect.width(), rect.height())
}

fun Path.getSegment(fraction: Float): Path {
    val newPath = Path()
    val pathMeasure = PathMeasure()
    pathMeasure.setPath(this, false)
    pathMeasure.getSegment(0f, pathMeasure.length * fraction, newPath)
    return newPath
}


@Composable
fun AnimatedText(text: String, modifier: Modifier, fontSize: TextUnit = 20.sp) {
    AnimatedContent(targetState = text, label = "", transitionSpec = {
        (slideInVertically { 80 } + fadeIn() togetherWith slideOutVertically { -80 } + fadeOut()).using(
            SizeTransform(false)
        )
    }, modifier = modifier.fillMaxWidth()) {
        Text(
            text = it,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize
        )
    }
}

@Composable
fun EqualizerCircles(played: Boolean) {
    val animatables = remember {
        Array(3) {
            Animatable(0f)
        }
    }
    LaunchedEffect(key1 = played) {
        if (played) {
            animatables.forEachIndexed { index, animatable ->
                launch {
//                    delay(200 * (animatables.size - index.plus(1)).toLong())
                    val endDelay = 200 * index
                    var isStartDelay = true
                    while (true) {
                        animatable.animateTo(1f, tween(1500))
                        if (isStartDelay){
                            delay(endDelay.toLong())
                            isStartDelay = false
                        }
                        animatable.animateTo(0f, tween(1500))
                    }
                }
            }
        } else {
            animatables.forEachIndexed { index, animatable ->
                launch {
                    delay(100 * index.toLong())
                    animatable.animateTo(0f, tween(1500))
                }
            }
        }
    }
    Canvas(modifier = Modifier.size(200.dp)) {
        animatables.forEachIndexed { index, animatable ->
            val radius = index.plus(1) * animatable.value * 80f + size.width.div(2)
            val color = Color.White.copy((.5f / (index.plus(1))).coerceIn(0f, 1f))
            drawCircle(color, radius)
        }
    }
}

@Composable
fun EqualizerCircles2(@SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    val animatables = remember {
        Array(3) {
            Animatable(0f)
        }
    }
    val alphas = remember {
        Array(3) {
            Animatable(1f)
        }
    }
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        animatables.forEachIndexed { index, animatable ->
            launch {
                delay(100 * (animatables.size - index.plus(1)).toLong())
                while (true) {
                    alphas[index].snapTo(1f)
                    animatable.snapTo(0f)
                    animatable.animateTo(1f, tween(1500))
                    animatable.animateTo(.8f, tween(100))
                    alphas[index].animateTo(0f, tween(200))
                    delay(500)
                }
            }
        }
    }
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .then(modifier)
    ) {
        animatables.forEachIndexed { index, animatable ->
            val alpha = alphas[index]
            val radius = index.plus(1) * animatable.value * 100f + size.width.div(2)
            val color = Color.White.copy((.5f / (index.plus(1))).coerceIn(0f, 1f))
            drawCircle(color, radius, alpha = alpha.value)
        }
    }
}

@Composable
fun EqualizerCirclesWithBorder(@SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    val animatables = remember {
        Array(2) {
            Animatable(0f)
        }
    }
    val strokes = remember {
        Array(2) {
            Animatable(0f)
        }
    }
    LaunchedEffect(key1 = Unit) {
        animatables.forEachIndexed { index, animatable ->
            launch {
                delay(500 * index.toLong())
                while (true) {
                    launch {
                        strokes[index].snapTo(0f)
                        strokes[index].animateTo(1f, tween(1000))
                        strokes[index].animateTo(0f, tween(200))
                    }
                    animatable.snapTo(0f)
                    animatable.animateTo(1f, tween(1500))
                    delay(500)
                }
            }
        }
    }
    Canvas(
        modifier = Modifier
            .size(200.dp)
            .then(modifier)
    ) {
        animatables.forEachIndexed { index, animatable ->
            val stroke = strokes[index]
            val radius = animatable.value * size.width * 2 / index.plus(1) + size.height * 0.8f
            val color = Color.White.copy(0.3f)
            if (stroke.value != 0f) {
                drawCircle(color, radius, style = Stroke(8.dp.toPx() * stroke.value))
            }
        }
    }
}

@Composable
fun LineEqualize(isPlay: Boolean, modifier: Modifier) {
    val animatables = remember {
        Array(5) {
            Animatable(0f)
        }
    }
    var incrementCircles by remember {
        mutableIntStateOf(0)
    }
    val circleOffsets = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = isPlay) {
        if (isPlay.not()) {
            while (true) {
                delay(1000)
                incrementCircles = 2
                circleOffsets.animateTo(1f, tween(2000))
                incrementCircles = 0
                circleOffsets.snapTo(0f)
            }
        } else {
            incrementCircles = 0
            circleOffsets.snapTo(0f)
        }
    }
    LaunchedEffect(key1 = isPlay) {
        if (isPlay) {
            animatables.forEach {
                launch {
                    while (true) {
                        it.animateTo(random(), tween(200))
                    }
                }
            }
        } else {
            animatables.forEach {
                launch {
                    it.animateTo(0f, tween(200))
                }
            }
        }
    }
    Canvas(
        modifier = modifier
            .width(42.dp)
            .height(20.dp)
    ) {
        val barWidth = 6.dp.toPx()
        val padding = size.width.minus(animatables.size * barWidth) / animatables.size.minus(1)
        if (incrementCircles != 0) {
            drawCircle(
                Color.White.copy(circleOffsets.value),
                barWidth.div(2),
                center = Offset(
                    barWidth * -1 + padding * -1 + barWidth.div(2) + circleOffsets.value * barWidth + circleOffsets.value * padding,
                    size.height.div(2)
                ),
            )
        }
        animatables.forEachIndexed { index, animatable ->
            if (animatable.value == 0f) {
                val alpha = if (circleOffsets.value != 0f && index.plus(1) == animatables.size) {
                    1 - circleOffsets.value
                } else 1f
                drawCircle(
                    Color.White,
                    barWidth.div(2),
                    center = Offset(
                        barWidth * index + padding * index + barWidth.div(2) + circleOffsets.value * barWidth + circleOffsets.value * padding,
                        size.height.div(2)
                    ),
                    alpha = alpha
                )
            } else {
                val height = size.height * animatable.value
                drawRoundRect(
                    Color.White,
                    topLeft = Offset(
                        barWidth * index + padding * index,
                        y = size.height.minus(height).div(2)
                    ),
                    size = Size(barWidth, height),
                    cornerRadius = CornerRadius(10f)
                )
            }
        }
    }
}


fun random() = (0..100).random() / 100f
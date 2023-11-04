(function () {
    //动态效果代码区块

    setTimeout(function () {

        //可以在外层定义变量：_DynamicParticleEffectClass 指定指定画布的class名称

        _DynamicParticleEffectClass = _DynamicParticleEffectClass || "dynamic-particle-effect-canvas";

        var canvas = document.querySelector("canvas." + _DynamicParticleEffectClass);

        console.debug("dynamic-particle-effect:", _DynamicParticleEffectClass, canvas);

        startDynamicParticleEffect(canvas);

    }, 200);

    function startDynamicParticleEffect(canvas) {

        if (!canvas) {
            //如果没有找到对应的画布
            return;
        }

        console.debug("start dynamic effect...");

        canvas.style = canvas.style.cssText + ";display:block; width:100%; height:100%; position: fixed; top: 0; left: 0;";

        var ctx = canvas.getContext("2d");

        canvas.width = window.innerWidth, canvas.height = window.innerHeight, ctx.lineWidth = .3, ctx.strokeStyle = new Color(150).style;
        var movePos = {x: 30 * canvas.width / 100, y: 30 * canvas.height / 100},
            dots = {nb: 250, distance: 100, d_radius: 150, array: []};

        function colorValue(t) {
            return Math.floor(255 * Math.random() + t)
        }

        function createColorStyle(t, o, i) {
            return "rgba(" + t + "," + o + "," + i + ", 0.618)"
        }

        function mixComponents(t, o, i, a) {
            return (t * o + i * a) / (o + a)
        }

        function averageColorStyles(t, o) {
            var i = t.color, a = o.color, s = mixComponents(i.r, t.radius, a.r, o.radius),
                n = mixComponents(i.g, t.radius, a.g, o.radius), e = mixComponents(i.b, t.radius, a.b, o.radius);
            return createColorStyle(Math.floor(s), Math.floor(n), Math.floor(e))
        }

        function Color(t) {
            t = t || 0, this.r = colorValue(t), this.g = colorValue(t), this.b = colorValue(t), this.style = createColorStyle(this.r, this.g, this.b)
        }

        function Dot() {
            this.x = Math.random() * canvas.width, this.y = Math.random() * canvas.height, this.vx = -.5 + Math.random(), this.vy = -.5 + Math.random(), this.radius = 3 * Math.random(), this.color = new Color
        }

        function createDots() {
            for (i = 0; i < dots.nb; i++) dots.array.push(new Dot)
        }

        function moveDots() {
            for (i = 0; i < dots.nb; i++) {
                var t = dots.array[i];
                t.y < 0 || t.y > canvas.height ? (t.vx = t.vx, t.vy = -t.vy) : (t.x < 0 || t.x > canvas.width) && (t.vx = -t.vx, t.vy = t.vy), t.x += t.vx, t.y += t.vy
            }
        }

        function connectDots() {
            for (i = 0; i < dots.nb; i++) for (j = 0; j < dots.nb; j++) i_dot = dots.array[i], j_dot = dots.array[j], i_dot.x - j_dot.x < dots.distance && i_dot.y - j_dot.y < dots.distance && i_dot.x - j_dot.x > -dots.distance && i_dot.y - j_dot.y > -dots.distance && i_dot.x - movePos.x < dots.d_radius && i_dot.y - movePos.y < dots.d_radius && i_dot.x - movePos.x > -dots.d_radius && i_dot.y - movePos.y > -dots.d_radius && (ctx.beginPath(), ctx.strokeStyle = averageColorStyles(i_dot, j_dot), ctx.moveTo(i_dot.x, i_dot.y), ctx.lineTo(j_dot.x, j_dot.y), ctx.stroke(), ctx.closePath())
        }

        function drawDots() {
            for (i = 0; i < dots.nb; i++) {
                dots.array[i].draw()
            }
        }

        function runDots() {
            ctx.clearRect(0, 0, canvas.width, canvas.height), moveDots(), connectDots(), drawDots(), requestAnimationFrame(runDots)
        }

        Dot.prototype = {
            draw: function () {
                ctx.beginPath(), ctx.fillStyle = this.color.style, ctx.arc(this.x, this.y, this.radius, 0, 3 * Math.PI, !1), ctx.fill()
            }
        };

        canvas.addEventListener("mousemove", function (t) {
            movePos.x = t.pageX, movePos.y = t.pageY
        }), canvas.addEventListener("mouseleave", function (t) {
            movePos.x = canvas.width / 2, movePos.y = canvas.height / 2
        }), createDots(), requestAnimationFrame(runDots);

        console.debug("start dynamic effect ok.")
    }

})();

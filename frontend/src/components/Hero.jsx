function Hero() {
  return (
    <section
      id="home"
      className="min-h-[90vh] flex items-center justify-center px-8"
    >
      <div className="max-w-6xl grid md:grid-cols-2 gap-16 items-center">

        <div>

          <span className="bg-emerald-100 text-emerald-700 px-4 py-2 rounded-full text-sm font-semibold">
            ✨ AI Powered Nutrition Assistant
          </span>

          <h1 className="text-6xl font-bold text-slate-800 mt-6 leading-tight">
            Personalized
            <br />

            Nutrition
            <br />

            Powered by AI.
          </h1>

          <p className="text-lg text-slate-500 mt-8 leading-8">

            Discover healthy meals tailored to your
            goals, preferences and lifestyle in seconds
            using Gemini AI.

          </p>

          <a href="#recommend">

            <button className="mt-10 bg-emerald-600 hover:bg-emerald-700 transition text-white px-8 py-4 rounded-xl text-lg font-semibold shadow-lg">

              🍽 Get Started

            </button>

          </a>

        </div>

        <div className="flex justify-center">

          <img
            src="https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=900"
            alt="Healthy Food"
            className="rounded-3xl shadow-2xl w-full max-w-lg"
          />

        </div>

      </div>
    </section>
  );
}

export default Hero;
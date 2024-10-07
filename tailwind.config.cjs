/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      boxShadow: {
        "clay-white":
          "15px 15px 40px 0px rgba(43, 154, 232, 0.5), inset -6px -6px 10px 0px rgba(43, 154, 232, 0.4), inset 0px 6px 14px 0px rgb(255, 255, 255)",
        "clay-blue":
          "15px 15px 40px 0px rgba(43, 154, 232, 0.5), inset -6px -6px 10px 0px rgba(43, 131, 232, 0.4), inset 0px 6px 14px 0px rgb(172, 212, 243)",
        "clay-blue-sm":
          "10px 10px 20px 0px rgba(43, 154, 232, 0.4), inset -6px -6px 10px 0px rgba(43, 131, 232, 0.4), inset 0px 6px 14px 0px rgb(172, 212, 243)",
      },
    },
  },
  plugins: [],
};

const path = require('path');

module.exports = {
  mode: 'production',
  entry: './target/compiled.js',
  output: {
    filename: 'packed.js',
    path: path.resolve(__dirname, 'packed'),
  },
};
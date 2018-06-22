import { IngredientsModule } from './ingredients.module';

describe('IngredientsModule', () => {
  let ingredientsModule: IngredientsModule;

  beforeEach(() => {
    ingredientsModule = new IngredientsModule();
  });

  it('should create an instance', () => {
    expect(ingredientsModule).toBeTruthy();
  });
});
